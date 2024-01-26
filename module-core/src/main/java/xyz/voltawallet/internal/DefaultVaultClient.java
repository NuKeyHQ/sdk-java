package xyz.voltawallet.internal;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.utils.Numeric;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.contracts.EntryPoint;
import xyz.voltawallet.contracts.VoltaAccount;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddressesConfig;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.utility.Utility;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

class DefaultVaultClient implements VaultClient {

  private final EntryPoint entryPoint;
  private final VoltaAccount voltaAccount;
  private final ContractAddressesConfig contractAddressesConfig;
  private final Web3j web3j;
  private long chainId = -1;

  DefaultVaultClient(EntryPoint entryPoint, VoltaAccount voltaAccount, ContractAddressesConfig contractAddressesConfig, Web3j web3j) {
    this.entryPoint = entryPoint;
    this.voltaAccount = voltaAccount;
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
  public UserOperation buildExecuteUserOperation(String sender, Call call) throws IOException, VoltaException {
    String callData = voltaAccount.execute(call.getTarget(), call.getValue(), call.getData()).encodeFunctionCall();
    return buildUsrOp(sender, callData);
  }

  @Override
  public UserOperation buildExecuteUserOperationFromTx(String sender, Transaction tx) throws IOException, VoltaException {
    return buildExecuteUserOperation(
      sender,
      new Call(
        tx.getTo(),
        Numeric.decodeQuantity(tx.getValue()),
        Numeric.hexStringToByteArray(tx.getData())
      )
    );
  }

  @Override
  public UserOperation buildExecuteBatchUserOperation(String sender, List<Call> calls) throws IllegalArgumentException, IOException, VoltaException {
    if (calls == null || calls.size() <= 1) throw new IllegalArgumentException("calls size must be greater than 1");
    List<VoltaAccount.Call> voltaCalls = calls.stream()
      .map(call -> new VoltaAccount.Call(
          call.getTarget(),
          call.getValue(),
          call.getData()
        )
      ).toList();
    String callData = voltaAccount.executeBatch(voltaCalls).encodeFunctionCall();
    return buildUsrOp(sender, callData);
  }

  @Override
  public UserOperation buildCustomUserOperation(String sender, byte[] callData) throws IllegalArgumentException, IOException, VoltaException {
    if (callData == null || callData.length == 0) throw new IllegalArgumentException("callData must not be null or empty");
    return buildUsrOp(sender, Numeric.toHexString(callData));
  }

  @Override
  public BigInteger suggestGasTipCap() throws IOException {
    return web3j.ethGasPrice().send().getGasPrice();
  }

  @Override
  public BigInteger getBaseFeePerGas() throws IOException {
    return web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
      .send()
      .getBlock()
      .getBaseFeePerGas();
  }

  private UserOperation buildUsrOp(String sender, String callData) throws IOException, VoltaException {
    if (!Utility.isHexAddress(sender)) throw new VoltaException("Sender address is invalid");
    long userOpChainId = getChainId();
    BigInteger nonce = nextNonce(sender);
    return UserOperation.builder()
      .setSender(sender)
      .setNonce(nonce)
      .setCallData(callData)
      .setInitCode("0x")
      .setCallGasLimit(BigInteger.ZERO)
      .setVerificationGasLimit(BigInteger.ZERO)
      .setPreVerificationGas(BigInteger.ZERO)
      .setMaxFeePerGas(BigInteger.ZERO)
      .setMaxPriorityFeePerGas(BigInteger.ZERO)
      .setChainId(userOpChainId)
      .setEntryPointAddress(contractAddressesConfig.getEntryPoint())
      .build();
  }

  private long getChainId() throws IOException {
    if (chainId == -1) {
      chainId = web3j.ethChainId().send().getChainId().longValue();
    }
    return chainId;
  }
}
