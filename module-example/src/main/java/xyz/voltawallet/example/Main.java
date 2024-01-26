package xyz.voltawallet.example;

import xyz.voltawallet.BundlerClient;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.VoltaSdk;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddressesConfig;
import xyz.voltawallet.model.EstimateFeeResponse;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.UserOperationReceiptResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * An example about using Volta sdk-java for building UserOperation of a Vault, broadcast the UsrOp and get its result.<br>
 */
public class Main {

  private static final List<String> prvKeys = List.of(
    "0x17e1f35a218f55b0da168295ea6f634edb75856b25903308320bd27327a89031",
    "0xce5f01849a41c79ee382266a710ab22e1bfe72a2fc9294ab4ef942e0caa1b7fb"
  );

  private static final ContractAddressesConfig contractAddressesConfig = ContractAddressesConfig.builder()
    .setFactory("0xE671b76a1e53Dc43d0D248A4794777F6718B1021")
    .build();

  private static final String sender = "0x6a7540011DCD63e2dfACBFdF140fCcd7a24cB78a";

  private static final String bundlerUrl = "https://api-bundler.dev.nukey.fi/polygon-mumbai";

  public static void main(String[] args) throws Exception {
    VaultClient vaultClient = VoltaSdk.newVaultClient(bundlerUrl, contractAddressesConfig);
    BundlerClient bundlerClient = VoltaSdk.newBundlerClient(bundlerUrl);

    // Building and sending a UsrOp to transfer some Matic
    UserOperation usrOp = vaultClient.buildExecuteUserOperation(sender, newTransferCall("0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", new BigDecimal("0.01")));
    System.out.println("--- User Operation to transfer some Matic ---");
    usrOp = suggestFeeFor(bundlerClient, vaultClient, usrOp);
    signAndSendUsrOp(bundlerClient, usrOp);

    // Building and sending a batch of UsrOp to transfer some Matic
    UserOperation batchCallUsrOp = vaultClient.buildExecuteBatchUserOperation(
      sender,
      List.of(
        newTransferCall("0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", new BigDecimal("0.001")),
        newTransferCall("0x91290e8969bc43f166317Ec664f53c2f484081c9", new BigDecimal("0.002")),
        newTransferCall("0x352eEDdc7162e3D8a258E305ed698CF8A2EC3cD6", new BigDecimal("0.003"))
      )
    );
    System.out.println("--- User Operation to transfer Matic to some address ---");
    batchCallUsrOp = suggestFeeFor(bundlerClient, vaultClient, batchCallUsrOp);
    signAndSendUsrOp(bundlerClient, batchCallUsrOp);
  }

  static Call newTransferCall(String toAddress, BigDecimal value) {
    return new Call(
      toAddress,
      value.movePointRight(18).toBigInteger(),
      null
    );
  }

  static UserOperation suggestFeeFor(BundlerClient bundlerClient, VaultClient vaultClient, final UserOperation userOperation) throws VoltaException, IOException {
    BigInteger gasTipCap = vaultClient.suggestGasTipCap();
    BigInteger baseFee = vaultClient.getBaseFeePerGas();
    UserOperation userOp = userOperation.buildUpon()
      .setMaxFeePerGas(gasTipCap.add(baseFee))
      .setMaxPriorityFeePerGas(gasTipCap)
      .build();

    EstimateFeeResponse estimateFeeResponse = bundlerClient.estimateUserOperationGas(userOp);
    return userOp.buildUpon()
      .setCallGasLimit(increaseByPercent(estimateFeeResponse.getCallGasLimit(), 20))
      .setPreVerificationGas(increaseByPercent(estimateFeeResponse.getPreVerificationGas(), 20))
      .setVerificationGasLimit(increaseByPercent(estimateFeeResponse.getVerificationGasLimit(), 20))
      .build();
  }

  static void signAndSendUsrOp(BundlerClient bundlerClient, UserOperation usrOp) throws VoltaException, IOException, InterruptedException {
    usrOp.sign(prvKeys.toArray(new String[0]));
    System.out.println(usrOp);

    String usrOpHash = bundlerClient.sendUserOperation(usrOp);
    System.out.println("User Operation hash: " + usrOpHash);

    Thread.sleep(10_000);
    UserOperationReceiptResponse receiptResponse = bundlerClient.getUserOperationReceipt(usrOpHash);
    System.out.println("User Operation result: " + (receiptResponse.isSuccess() ? "success" : "failed") + ", tx hash -> " + receiptResponse.getReceipt().getTransactionHash());
  }

  private static BigInteger increaseByPercent(BigInteger input, int percent) {
    return BigInteger.valueOf(100 + percent).multiply(input).divide(BigInteger.valueOf(100));
  }
}
