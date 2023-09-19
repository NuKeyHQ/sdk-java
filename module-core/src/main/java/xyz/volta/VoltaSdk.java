package xyz.volta;

import xyz.volta.internal.VoltaFactory;

public class VoltaSdk {

  public static synchronized VoltaClient clientFor(String bundleServiceUrl) {
    return VoltaFactory.provideVoltaClient(bundleServiceUrl);
  }
}
