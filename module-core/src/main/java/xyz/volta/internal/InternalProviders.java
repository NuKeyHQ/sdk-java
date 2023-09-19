package xyz.volta.internal;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import xyz.volta.VoltaSessionKeyClient;

public final class InternalProviders {

  private static VoltaSessionKeyClient instance = null;

  private static ObjectMapper provideObjectMapper() {
    return (new ObjectMapper())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private static OkHttpClient provideOkHttpClient() {
    return new OkHttpClient();
  }

  private static BundleApiClient provideBundleApiClient(String bundleServiceUrl) {
    return new BundleApiClientImpl(
      provideObjectMapper(),
      provideOkHttpClient(),
      bundleServiceUrl
    );
  }

  public static synchronized VoltaSessionKeyClient provideVoltaSessionKeyClient(String bundleServiceUrl) {
    if (instance == null) {
      instance = new VoltaSessionKeyClientImpl(
        provideBundleApiClient(bundleServiceUrl)
      );
    }
    return instance;
  }
}
