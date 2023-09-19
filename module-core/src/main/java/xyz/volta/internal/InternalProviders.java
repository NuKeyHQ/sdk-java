package xyz.volta.internal;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import xyz.volta.VoltaClient;

public final class InternalProviders {

  private static VoltaClient instance = null;

  private static ObjectMapper provideObjectMapper() {
    return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private static OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

  private static BundleClient provideBundleApiClient(String bundleServiceUrl) {
    return new DefaultBundleClient(
      provideObjectMapper(),
      provideOkHttpClient(),
      bundleServiceUrl
    );
  }

  public static synchronized VoltaClient provideVoltaSessionKeyClient(String bundleServiceUrl) {
    if (instance == null) {
      instance = new DefaultVoltaClient(
        provideBundleApiClient(bundleServiceUrl)
      );
    }
    return instance;
  }
}
