package xyz.voltawallet.internal;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.utils.Numeric;
import xyz.voltawallet.BundlerClient;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.contracts.EntryPoint;
import xyz.voltawallet.contracts.VoltaAccount;
import xyz.voltawallet.contracts.VoltaFactory;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddressesConfig;
import xyz.voltawallet.model.EstimateFeeResponse;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class DefaultVaultClient implements VaultClient {

  private final EntryPoint entryPoint;
  private final VoltaFactory voltaFactory;
  private final VoltaAccount voltaAccount;
  private final BundlerClient bundlerClient;
  private final ContractAddressesConfig contractAddressesConfig;
  private final Web3j web3j;
  private long chainId = -1;

  DefaultVaultClient(EntryPoint entryPoint, VoltaFactory voltaFactory, VoltaAccount voltaAccount, BundlerClient bundlerClient,
                     ContractAddressesConfig contractAddressesConfig, Web3j web3j) {
    this.entryPoint = entryPoint;
    this.voltaFactory = voltaFactory;
    this.voltaAccount = voltaAccount;
    this.bundlerClient = bundlerClient;
    this.contractAddressesConfig = contractAddressesConfig;
    this.web3j = web3j;
  }

  @Override
  public BigInteger nextNonce(String senderAddress) {
    try {
      return entryPoint.getNonce(senderAddress, BigInteger.ZERO).send();
    } catch (Exception e) {
      return BigInteger.ZERO;
    }
  }

  @Override
  public UserOperation buildExecuteUserOperation(Vault vault, Call call) throws IOException, VoltaException {
    String callData = voltaAccount.execute(call.getTarget(), call.getValue(), call.getData()).encodeFunctionCall();
    return buildUsrOp(vault, callData);
  }

  @Override
  public UserOperation buildExecuteUserOperationFromTx(Vault vault, Transaction tx) throws IOException, VoltaException {
    return buildExecuteUserOperation(
      vault,
      new Call(
        tx.getTo(),
        Numeric.decodeQuantity(tx.getValue()),
        Numeric.hexStringToByteArray(tx.getData())
      )
    );
  }

  @Override
  public UserOperation buildExecuteBatchUserOperation(Vault vault, List<Call> calls) throws IllegalArgumentException, IOException, VoltaException {
    if (calls == null || calls.isEmpty()) throw new IllegalArgumentException("calls must not be null or empty");
    List<VoltaAccount.Call> voltaCalls = calls.stream()
      .map(call -> new VoltaAccount.Call(
          call.getTarget(),
          call.getValue(),
          call.getData()
        )
      ).toList();
    String callData = voltaAccount.executeBatch(voltaCalls).encodeFunctionCall();
    return buildUsrOp(vault, callData);
  }

  @Override
  public UserOperation buildCustomUserOperation(Vault vault, byte[] callData) throws IllegalArgumentException, IOException, VoltaException {
    if (callData == null || callData.length == 0) throw new IllegalArgumentException("callData must not be null or empty");
    return buildUsrOp(vault, Numeric.toHexString(callData));
  }

  @Override
  public UserOperation suggestUserOpGasPrice(UserOperation userOperation) throws IOException {
    BigInteger gasTipCap = web3j.ethMaxPriorityFeePerGas().send().getMaxPriorityFeePerGas();
    if (gasTipCap.compareTo(BigInteger.ZERO) <= 0) {
      gasTipCap = BigInteger.ONE;
    }
    EthBlock block = web3j.ethGetBlockByNumber(
      DefaultBlockParameter.valueOf(DefaultBlockParameterName.LATEST.name()),
      false
    ).send();
    return userOperation.buildUpon()
      .setMaxPriorityFeePerGas(gasTipCap)
      .setMaxFeePerGas(gasTipCap.add(block.getBlock().getBaseFeePerGas()))
      .build();
  }

  private UserOperation buildUsrOp(Vault vault, String callData) throws IOException, VoltaException {
    long userOpChainId = getChainId();
    String sender = getAccountAddress(vault);
    BigInteger nonce = nextNonce(sender);
    String initCode = "0x";
    if (nonce.compareTo(BigInteger.ZERO) == 0) {
      initCode = getInitCode(vault);
    }
    UserOperation userOperation = UserOperation.builder()
      .setSender(sender)
      .setNonce(nonce)
      .setCallData(callData)
      .setInitCode(initCode)
      .setCallGasLimit(BigInteger.valueOf(1_000_000L))
      .setVerificationGasLimit(BigInteger.valueOf(1054982))
      .setPreVerificationGas(BigInteger.valueOf(700000))
      .setMaxFeePerGas(BigInteger.TWO)
      .setMaxPriorityFeePerGas(BigInteger.ONE)
      .setChainId(userOpChainId)
      .setEntryPointAddress(contractAddressesConfig.getEntryPoint())
      .build();

    try {
      userOperation = suggestUserOpGasPrice(userOperation);
    } catch (Exception ignored) {
    }
    return estimateGas(userOperation, vault.getThreshold().longValue());
  }

  private String getInitCode(Vault vault) {
    String createAccountEncoded = voltaFactory.createAccount(
      contractAddressesConfig.getAccountImplementation(),
      contractAddressesConfig.getSessionKeyValidatorImplementation(),
      contractAddressesConfig.getExecutorImplementation(),
      vault.getOwners(),
      vault.getThreshold(),
      vault.getSeed()
    ).encodeFunctionCall();
    return contractAddressesConfig.getFactory().toLowerCase(Locale.ROOT) + createAccountEncoded.replace("0x", "");
  }

  private String getAccountAddress(Vault vault) throws IOException, VoltaException {
    try {
      return voltaFactory.getAddress(
        contractAddressesConfig.getAccountImplementation(),
        contractAddressesConfig.getSessionKeyValidatorImplementation(),
        contractAddressesConfig.getExecutorImplementation(),
        vault.getOwners(),
        vault.getThreshold(),
        vault.getSeed()
      ).send();
    } catch (Exception e) {
      if (e instanceof IOException) {
        throw (IOException) e;
      } else {
        e.printStackTrace();
        throw new VoltaException(e.getMessage());
      }
    }
  }

  private long getChainId() throws IOException {
    if (chainId == -1) {
      chainId = web3j.ethChainId().send().getChainId().longValue();
    }
    return chainId;
  }

  private UserOperation estimateGas(UserOperation userOperation, long vaultThreshold) {
    try {
      // Estimated gas of User Operation without Signature may return incorrect value, so we add a fake signature for estimate the approximate gas.
      List<String> fakePKs = new ArrayList<>();
      for (int i = 0; i < vaultThreshold; i++) {
        fakePKs.add(Numeric.toHexStringWithPrefix(BigInteger.valueOf(i)));
      }
      UserOperation usrOpForEstimate = userOperation.buildUpon().build();
      usrOpForEstimate.sign(fakePKs.toArray(new String[0]));

      EstimateFeeResponse feeResponse = bundlerClient.estimateUserOperationGas(usrOpForEstimate, contractAddressesConfig.getEntryPoint());
      return userOperation.buildUpon()
        .setPreVerificationGas(feeResponse.getPreVerificationGas())
        .setVerificationGasLimit(feeResponse.getVerificationGas())
        .setCallGasLimit(feeResponse.getCallGasLimit())
        .build();
    } catch (Exception ignored) {
      return userOperation;
    }
  }
}
