package xyz.volta.constant;

public enum Blockchain {
  ETHEREUM_MAINNET(
    "ethereum-mainnet",
    1
  ),
  ETHEREUM_GOERLI(
    "ethereum-goerli",
    5
  ),
  POLYGON_MUMBAI(
    "polygon-mumbai",
    80001
  );

  public static final String DEFAULT_ENTRY_POINT_ADDRESS = "0x5FF137D4b0FDCD49DcA30c7CF57E578a026d2789";

  public final String chainName;
  public final int chainId;

  Blockchain(String chainName, int chainId) {
    this.chainName = chainName;
    this.chainId = chainId;
  }
}
