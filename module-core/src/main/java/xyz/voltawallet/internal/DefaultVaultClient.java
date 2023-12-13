package xyz.voltawallet.internal;

import xyz.voltawallet.VaultClient;
import xyz.voltawallet.contracts.EntryPoint;
import xyz.voltawallet.contracts.VoltaAccount;
import xyz.voltawallet.contracts.VoltaFactory;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.internal.model.EstimateFeeResponse;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddresses;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Locale;

class DefaultVaultClient implements VaultClient {

  private final EntryPoint entryPoint;
  private final VoltaFactory voltaFactory;
  private final VoltaAccount voltaAccount;
  private final BundleClient bundleClient;
  private final ContractAddresses contractAddresses;

  public DefaultVaultClient(EntryPoint entryPoint, VoltaFactory voltaFactory, VoltaAccount voltaAccount, BundleClient bundleClient, ContractAddresses contractAddresses) {
    this.entryPoint = entryPoint;
    this.voltaFactory = voltaFactory;
    this.voltaAccount = voltaAccount;
    this.bundleClient = bundleClient;
    this.contractAddresses = contractAddresses;
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
  public UserOperation buildExecuteUserOp(Vault vault, Call call) throws IOException, VoltaException {
    String callData = voltaAccount.execute(call.getTarget(), call.getValue(), call.getData()).encodeFunctionCall();
    return buildUsrOp(vault, callData);
  }

  private UserOperation buildUsrOp(Vault vault, String callData) throws IOException, VoltaException {
    String sender = getAccountAddress(vault);
    BigInteger gasTipCap = bundleClient.suggestGasTipCap();
    if (gasTipCap.compareTo(BigInteger.ZERO) <= 0) {
      gasTipCap = BigInteger.ONE;
    }
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
      .setMaxFeePerGas(gasTipCap.add(getLastBlockBaseFee()))
      .setMaxPriorityFeePerGas(gasTipCap)
      .build();
    try {
      EstimateFeeResponse feeResponse = bundleClient.estimateUserOperationGas(userOperation, contractAddresses.getEntryPoint());
      return userOperation.copyToBuilder()
        .setPreVerificationGas(feeResponse.getPreVerificationGas())
        .setVerificationGasLimit(feeResponse.getVerificationGas())
        .setCallGasLimit(feeResponse.getCallGasLimit())
        .build();
    } catch (Exception ignored) {
      return userOperation;
    }
  }

  private String getInitCode(Vault vault) {
    String createAccountEncoded = voltaFactory.createAccount(
      contractAddresses.getAccountImplementation(),
      contractAddresses.getSessionKeyValidatorImplementation(),
      contractAddresses.getExecutorImplementation(),
      vault.getOwners(),
      vault.getThreshold(),
      vault.getSeed()
    ).encodeFunctionCall();
    return contractAddresses.getFactory().toLowerCase(Locale.ROOT) + createAccountEncoded.replace("0x", "");
  }

  private String getAccountAddress(Vault vault) throws IOException, VoltaException {
    try {
      return voltaFactory.getAddress(
        contractAddresses.getAccountImplementation(),
        contractAddresses.getSessionKeyValidatorImplementation(),
        contractAddresses.getExecutorImplementation(),
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

  private BigInteger getLastBlockBaseFee() {
    try {
      return bundleClient.lastBlockInfo().getBaseFeePerGas();
    } catch (Exception ignored) {
      return BigInteger.ONE;
    }
  }
}
