package xyz.volta.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import xyz.volta.constant.Blockchain;
import xyz.volta.internal.model.EstimateFeeResponse;
import xyz.volta.internal.model.JsonRpcMessage;
import xyz.volta.model.UserOperation;
import xyz.volta.utility.Utility;

import java.io.IOException;
import java.util.List;

class DefaultBundleClient implements BundleClient {

  private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
  private final ObjectMapper objectMapper;
  private final OkHttpClient client;
  private final String bundleServiceUrl;

  DefaultBundleClient(
    final ObjectMapper objectMapper,
    final OkHttpClient client,
    final String bundleServiceUrl
  ) {
    this.objectMapper = objectMapper;
    this.client = client;
    this.bundleServiceUrl = bundleServiceUrl;
  }

  @Override
  public EstimateFeeResponse estimateUserOperationGas(
    final Blockchain blockchain,
    final UserOperation operation,
    final String entryPoint
  ) throws IOException {
    return execute(
      blockchain,
      "eth_estimateUserOperationGas",
      List.of(operation, entryPoint),
      EstimateFeeResponse.class
    );
  }

  @Override
  public Object sendUserOperation(
    final Blockchain blockchain,
    final UserOperation operation,
    final String entryPoint
  ) throws IOException {
    return execute(blockchain, "eth_sendUserOperation", List.of(operation, entryPoint), Object.class);
  }

  private <T> T execute(Blockchain blockchain, String method, Object params, Class<T> type) throws IOException {
    final String url = urlFor(blockchain);
    if (Utility.isNullOrBlank(url)) {
      throw new IllegalArgumentException(String.format("no url for blockchain %s", blockchain));
    }
    final JsonRpcMessage<Object> message = new JsonRpcMessage<>(
      "2.0",
      method,
      params,
      1
    );
    final Request request = new Request.Builder()
      .url(url)
      .post(RequestBody.create(objectMapper.writeValueAsString(message), JSON_MEDIA_TYPE))
      .build();
    try (Response response = client.newCall(request).execute()) {
      final ResponseBody body = response.body();
      if (response.isSuccessful()) {
        if (body == null) {
          return null;
        } else {
          return objectMapper.readValue(body.string(), type);
        }
      } else {
        throw new IOException("Failed to execute request: " + response);
      }
    }
  }

  private String urlFor(Blockchain blockchain) {
    return bundleServiceUrl;
  }
}
