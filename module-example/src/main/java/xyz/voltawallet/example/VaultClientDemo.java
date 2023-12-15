package xyz.voltawallet.example;

import xyz.voltawallet.VaultClient;
import xyz.voltawallet.VoltaSdk;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.ContractAddressesConfig;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class VaultClientDemo {
  public static void main(String[] args) throws Exception {
    ContractAddressesConfig contractAddressesConfig = new ContractAddressesConfig(
      "0xB1313Cf059c84Fbd9d825Ba7583727c8f42f2269",
      "0x824dFa52C6C385cca7F0CDE0daC858090884B444",
      "0xDe199357071f75fa0b18e39187497429601513b3",
      "0x474f2dB5630717C42C113CC8b1bd6a6c65380384"
    );
    VaultClient vaultClient = VoltaSdk.newVaultClient("https://api-bundler.dev.nukey.fi/polygon-mumbai", contractAddressesConfig);
    BigInteger nonce = vaultClient.nextNonce("0xbDf5302c6A2865Ca45Ea3f629E985eA8D83f886b");
    System.out.println("Nonce -> " + nonce);

    BigDecimal value = new BigDecimal("0.01");
    BigInteger valueInWei = value.movePointRight(18).toBigInteger();
    Call call = new Call("0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", valueInWei, null);

    Vault vault = new Vault(List.of("0x352eEDdc7162e3D8a258E305ed698CF8A2EC3cD6", "0xb56571C4944D65cEAdd424bfCC08d155b7eCd4c9", "0x91290e8969bc43f166317Ec664f53c2f484081c9"), 2, BigInteger.ONE);

    UserOperation usrOp = vaultClient.buildExecuteUserOp(vault, call);
    System.out.println("User Operation");
    System.out.println(usrOp);
  }
}
