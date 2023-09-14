package xyz.volta.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import xyz.volta.VoltaSessionKeyClient;

public final class InternalProviders {
    private static ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    private static BundleApiClient provideBundleApiClient() {
        return new BundleApiClientImpl(
                provideObjectMapper(),
                provideOkHttpClient()
        );
    }

    static VoltaSessionKeyClient provideVoltaSessionKeyClient() {
        return new VoltaSessionKeyClientImpl(
                provideBundleApiClient()
        );
    }
}
