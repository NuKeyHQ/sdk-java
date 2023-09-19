package xyz.volta.constant;

public enum Blockchain {
  ETHEREUM_MAINNET("ethereum-mainnet", 1),
  ETHEREUM_GOERLI("ethereum-goerli", 5),
  POLYGON_MUMBAI("polygon-mumbai", 80001);
  private final String chainName;
  private final int chainId;

  Blockchain(String chainName, int chainId) {
    this.chainName = chainName;
    this.chainId = chainId;
  }

  public static String defaultEntryPointAddress() {
    return "0x5FF137D4b0FDCD49DcA30c7CF57E578a026d2789";
  }

  public String getChainName() {
    return chainName;
  }

  public int getChainId() {
    return chainId;
  }
}
