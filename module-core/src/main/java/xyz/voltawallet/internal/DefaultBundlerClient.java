package xyz.voltawallet.internal;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import xyz.voltawallet.BundlerClient;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.EstimateFeeResponse;
import xyz.voltawallet.internal.model.JsonRpcMessage;
import xyz.voltawallet.model.UserOperationReceiptResponse;
import xyz.voltawallet.model.UserOperation;

import java.io.IOException;
import java.util.List;

class DefaultBundlerClient implements BundlerClient {

  private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");
  private final ObjectMapper objectMapper;
  private final OkHttpClient client;
  private final String bundleServiceUrl;

  DefaultBundlerClient(
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
    final UserOperation operation,
    final String entryPoint
  ) throws IOException, VoltaException {
    return execute(
      "eth_estimateUserOperationGas",
      List.of(operation, entryPoint),
      EstimateFeeResponse.class
    );
  }

  @Override
  public String sendUserOperation(
    final UserOperation operation
  ) throws IOException, VoltaException {
    return execute("eth_sendUserOperation", List.of(operation, operation.getEntryPointAddress()), String.class);
  }

  @Override
  public UserOperationReceiptResponse getUserOperationReceipt(String userOpHash) throws IOException, VoltaException {
    return execute("eth_getUserOperationReceipt", List.of(userOpHash), UserOperationReceiptResponse.class);
  }

  private <T> T execute(String method, Object params, Class<T> type) throws IOException, VoltaException {
    final JsonRpcMessage<Object> message = new JsonRpcMessage<>(
      "2.0",
      method,
      params,
      1
    );
    final Request request = new Request.Builder()
      .url(bundleServiceUrl)
      .post(RequestBody.create(objectMapper.writeValueAsString(message), JSON_MEDIA_TYPE))
      .build();
    try (Response response = client.newCall(request).execute()) {
      final ResponseBody body = response.body();
      if (response.isSuccessful()) {
        if (body == null) {
          return null;
        } else {
          JavaType resultType = objectMapper.getTypeFactory().constructParametricType(JsonRpcMessage.class, type);
          JsonRpcMessage<T> result = objectMapper.readValue(body.string(), resultType);
          if (result.getError() != null) {
            throw new VoltaException(result.getError().getMessage(), result.getError().getCode());
          } else {
            return result.getResult();
          }
        }
      } else {
        throw new IOException("Failed to execute request: " + response);
      }
    }
  }
}
