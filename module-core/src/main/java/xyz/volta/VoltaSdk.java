package xyz.volta;

import xyz.volta.internal.InternalProviders;

public class VoltaSdk {

  public static synchronized VoltaSessionKeyClient initSessionKeyClient(String bundleServiceUrl) {
    return InternalProviders.provideVoltaSessionKeyClient(bundleServiceUrl);
  }
}
