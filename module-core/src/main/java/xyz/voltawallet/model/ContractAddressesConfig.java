package xyz.voltawallet.model;

import xyz.voltawallet.utility.Utility;

public class ContractAddressesConfig {

  static final String DEFAULT_ENTRY_POINT_ADDRESS = "0x5FF137D4b0FDCD49DcA30c7CF57E578a026d2789";

  private final String factory;
  private final String entryPoint;

  private ContractAddressesConfig(String factory, String entryPoint) {
    if (!Utility.isHexAddress(factory)) throw new IllegalArgumentException("factory must be a hex address");
    if (entryPoint != null && !Utility.isHexAddress(entryPoint)) throw new IllegalArgumentException("entryPoint must be a hex address");
    this.factory = factory;
    this.entryPoint = entryPoint;
  }

  public String getFactory() {
    return factory;
  }

  public String getEntryPoint() {
    return entryPoint != null ? entryPoint : DEFAULT_ENTRY_POINT_ADDRESS;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String factory;
    private String entryPoint = DEFAULT_ENTRY_POINT_ADDRESS;

    public Builder setFactory(String factory) {
      this.factory = factory;
      return this;
    }

    public Builder setEntryPoint(String entryPoint) {
      this.entryPoint = entryPoint;
      return this;
    }

    public ContractAddressesConfig build() {
      return new ContractAddressesConfig(
        factory,
        entryPoint
      );
    }
  }
}
