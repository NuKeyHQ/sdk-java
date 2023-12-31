package xyz.voltawallet;

import xyz.voltawallet.internal.VoltaSdkProvider;
import xyz.voltawallet.model.ContractAddressesConfig;

public class VoltaSdk {

  /**
   * Returns a new instance of {@link BundlerClient} for the given bundle service URL.
   *
   * @param bundleServiceUrl The URL of the bundle service must not be null or blank.
   * @return A new instance of {@link BundlerClient}.
   */
  public static BundlerClient newBundlerClient(String bundleServiceUrl) {
    if (bundleServiceUrl == null || bundleServiceUrl.isBlank()) {
      throw new IllegalArgumentException("Bundle service URL cannot be null or blank");
    }
    return VoltaSdkProvider.createBundlerClient(bundleServiceUrl);
  }

  public static VaultClient newVaultClient(String bundleServiceUrl, ContractAddressesConfig contractAddressesConfig) {
    return VoltaSdkProvider.createVaultClient(bundleServiceUrl, contractAddressesConfig);
  }
}
