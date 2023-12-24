package xyz.voltawallet.example;

import xyz.voltawallet.BundlerClient;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.VoltaSdk;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddressesConfig;
import xyz.voltawallet.model.UserOperationReceiptResponse;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * An example about using Volta sdk-java for building UserOperation of a Vault, broadcast the UsrOp and get its result.<br>
 */
public class Main {

  private static final List<String> ownerAddress = List.of(
    "0xFABBE1a2D1374B5748461D7BC8C06a82Ad9a8822",
    "0xE6E032F418a6173c42F525EDae4CB8259471e708",
    "0x0666A5e68f52B9Feff7Dbe38A0a389AE72bab690",
    "0x4EE5DF6583A97c2588A2Ff1E5D5Aaf5f1aE3B415"
  );

  private static final List<String> prvKeys = List.of(
    "0xeb5664841d4c163175c45ea623e64b33599856c6116fcae603e01c8f508862b0",
    "0x5d004a74e5e789f4df744f51afd29eac261bb84b66d59f0861b758e2de039e35"
  );

  private static final ContractAddressesConfig contractAddressesConfig = ContractAddressesConfig.builder()
    .setFactory("0xB1313Cf059c84Fbd9d825Ba7583727c8f42f2269")
    .setAccountImplementation("0x824dFa52C6C385cca7F0CDE0daC858090884B444")
    .setSessionKeyValidatorImplementation("0xDe199357071f75fa0b18e39187497429601513b3")
    .setExecutorImplementation("0x474f2dB5630717C42C113CC8b1bd6a6c65380384")
    .build();

  private static final String bundlerUrl = "https://api-bundler.dev.nukey.fi/polygon-mumbai";

  public static void main(String[] args) throws Exception {
    VaultClient vaultClient = VoltaSdk.newVaultClient(bundlerUrl, contractAddressesConfig);
    BundlerClient bundlerClient = VoltaSdk.newBundlerClient(bundlerUrl);
    Vault vault = new Vault(ownerAddress, 2, BigInteger.ONE);

    // Building and sending a UsrOp to transfer some Matic
    UserOperation usrOp = vaultClient.buildExecuteUserOperation(vault, newTransferCall("0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", new BigDecimal("0.01")));
    System.out.println("--- User Operation to transfer some Matic ---");
    signAndSendUsrOp(bundlerClient, usrOp);

    // Building and sending a batch of UsrOp to transfer some Matic
    UserOperation batchCallUsrOp = vaultClient.buildExecuteBatchUserOperation(
      vault,
      List.of(
        newTransferCall("0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", new BigDecimal("0.001")),
        newTransferCall("0x91290e8969bc43f166317Ec664f53c2f484081c9", new BigDecimal("0.002")),
        newTransferCall("0x352eEDdc7162e3D8a258E305ed698CF8A2EC3cD6", new BigDecimal("0.003"))
      )
    );
    System.out.println("--- User Operation to transfer Matic to some address ---");
    signAndSendUsrOp(bundlerClient, batchCallUsrOp);
  }

  static Call newTransferCall(String toAddress, BigDecimal value) {
    return new Call(
      toAddress,
      value.movePointRight(18).toBigInteger(),
      null
    );
  }

  static void signAndSendUsrOp(BundlerClient bundlerClient, UserOperation usrOp) throws VoltaException, IOException, InterruptedException {
    usrOp.sign(prvKeys.toArray(new String[0]));
    System.out.println(usrOp);

    String usrOpHash = bundlerClient.sendUserOperation(usrOp);
    System.out.println("User Operation hash: " + usrOpHash);

    Thread.sleep(10_000);
    UserOperationReceiptResponse receiptResponse = bundlerClient.getUserOperationReceipt(usrOpHash);
    System.out.println("User Operation result: " + receiptResponse.isSuccess());
  }
}
