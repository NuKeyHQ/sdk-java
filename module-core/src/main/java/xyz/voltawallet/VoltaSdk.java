package xyz.voltawallet;

import xyz.voltawallet.internal.VoltaFactory;
import xyz.voltawallet.model.ContractAddressesConfig;

public class VoltaSdk {

  /**
   * Returns a new instance of {@link VoltaClient} for the given bundle service URL.
   *
   * @param bundleServiceUrl The URL of the bundle service must not be null or blank.
   * @return A new instance of {@link VoltaClient}.
   */
  public static VoltaClient newVoltaClient(String bundleServiceUrl) {
    if (bundleServiceUrl == null || bundleServiceUrl.isBlank()) {
      throw new IllegalArgumentException("Bundle service URL cannot be null or blank");
    }
    return VoltaFactory.createVoltaClient(bundleServiceUrl);
  }

  public static VaultClient newVaultClient(String bundleServiceUrl, ContractAddressesConfig contractAddressesConfig) {
    return VoltaFactory.createVaultClient(bundleServiceUrl, contractAddressesConfig);
  }
}
