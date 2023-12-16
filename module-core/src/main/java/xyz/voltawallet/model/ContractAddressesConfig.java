package xyz.voltawallet.model;

import xyz.voltawallet.utility.Utility;

public class ContractAddressesConfig {

  static final String DEFAULT_ENTRY_POINT_ADDRESS = "0x5FF137D4b0FDCD49DcA30c7CF57E578a026d2789";

  private final String factory;
  private final String accountImplementation;
  private final String sessionKeyValidatorImplementation;
  private final String executorImplementation;
  private final String entryPoint;

  private ContractAddressesConfig(String factory, String accountImplementation, String sessionKeyValidatorImplementation, String executorImplementation,
                                  String entryPoint) {
    if (!Utility.isHexAddress(factory)) throw new IllegalArgumentException("factory must be a hex address");
    if (!Utility.isHexAddress(accountImplementation)) throw new IllegalArgumentException("accountImplementation must be a hex address");
    if (!Utility.isHexAddress(sessionKeyValidatorImplementation)) throw new IllegalArgumentException("sessionKeyValidatorImplementation must be a hex address");
    if (!Utility.isHexAddress(executorImplementation)) throw new IllegalArgumentException("executorImplementation must be a hex address");
    if (entryPoint != null && !Utility.isHexAddress(entryPoint)) throw new IllegalArgumentException("entryPoint must be a hex address");
    this.factory = factory;
    this.accountImplementation = accountImplementation;
    this.sessionKeyValidatorImplementation = sessionKeyValidatorImplementation;
    this.executorImplementation = executorImplementation;
    this.entryPoint = entryPoint;
  }

  public String getFactory() {
    return factory;
  }

  public String getAccountImplementation() {
    return accountImplementation;
  }

  public String getSessionKeyValidatorImplementation() {
    return sessionKeyValidatorImplementation;
  }

  public String getExecutorImplementation() {
    return executorImplementation;
  }

  public String getEntryPoint() {
    return entryPoint != null ? entryPoint : DEFAULT_ENTRY_POINT_ADDRESS;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String factory;
    private String accountImplementation;
    private String sessionKeyValidatorImplementation;
    private String executorImplementation;
    private String entryPoint = DEFAULT_ENTRY_POINT_ADDRESS;

    public Builder setFactory(String factory) {
      this.factory = factory;
      return this;
    }

    public Builder setAccountImplementation(String accountImplementation) {
      this.accountImplementation = accountImplementation;
      return this;
    }

    public Builder setSessionKeyValidatorImplementation(String sessionKeyValidatorImplementation) {
      this.sessionKeyValidatorImplementation = sessionKeyValidatorImplementation;
      return this;
    }

    public Builder setExecutorImplementation(String executorImplementation) {
      this.executorImplementation = executorImplementation;
      return this;
    }

    public Builder setEntryPoint(String entryPoint) {
      this.entryPoint = entryPoint;
      return this;
    }

    public ContractAddressesConfig build() {
      return new ContractAddressesConfig(
        factory,
        accountImplementation,
        sessionKeyValidatorImplementation,
        executorImplementation,
        entryPoint
      );
    }
  }
}
