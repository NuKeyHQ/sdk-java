package xyz.volta;

import xyz.volta.internal.InternalProviders;

public class VoltaSdk {

  public static synchronized VoltaClient clientFor(String bundleServiceUrl) {
    return InternalProviders.provideVoltaSessionKeyClient(bundleServiceUrl);
  }
}
