package xyz.volta;

import xyz.volta.internal.VoltaFactory;

public class VoltaSdk {

  public static synchronized VoltaClient clientFor(String bundleServiceUrl) {
    if (bundleServiceUrl == null || bundleServiceUrl.isBlank()) {
      throw new IllegalArgumentException("Bundle service URL cannot be null or blank");
    }
    return VoltaFactory.createVoltaClient(bundleServiceUrl);
  }
}
