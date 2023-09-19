package xyz.volta.internal;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import xyz.volta.VoltaClient;

public final class VoltaFactory {

  private static final ObjectMapper JACKSON = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private static final OkHttpClient OKHTTP = new OkHttpClient();

  private static volatile VoltaClient instance = null;

  /**
   * Provide a singleton instance of {@link VoltaClient}
   *
   * @param bundleServiceUrl The url of the bundle service
   * @return A singleton instance of {@link VoltaClient}
   */
  public static VoltaClient provideVoltaClient(String bundleServiceUrl) {
    if (instance == null) {
      synchronized (VoltaFactory.class) {
        if (instance == null) {
          instance = new DefaultVoltaClient(
            new DefaultBundleClient(
              JACKSON,
              OKHTTP,
              bundleServiceUrl
            )
          );
        }
      }
    }
    return instance;
  }
}
