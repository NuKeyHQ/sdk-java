package xyz.volta.internal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import okhttp3.*;
import xyz.volta.constant.Blockchain;
import xyz.volta.exception.VoltaException;
import xyz.volta.internal.model.EstimateFeeResponse;
import xyz.volta.internal.model.JsonRpcMessage;
import xyz.volta.internal.model.VoltaError;
import xyz.volta.model.UserOperation;
import xyz.volta.utils.Utils;

import java.util.List;

class DefaultBundleClient implements BundleClient {

  private final ObjectMapper objectMapper;

  private final OkHttpClient client;

  private final String bundleServiceUrl;

  DefaultBundleClient(
    ObjectMapper objectMapper,
    OkHttpClient client,
    String bundleServiceUrl
  ) {
    this.objectMapper = objectMapper;
    this.client = client;
    this.bundleServiceUrl = bundleServiceUrl;
  }

  @Override
  public Single<EstimateFeeResponse> estimateUserOperationGas(
    Blockchain blockchain,
    UserOperation operation,
    String entryPoint
  ) {
    return jsonRpc(blockchain, "eth_estimateUserOperationGas", List.of(operation, entryPoint))
      .map(response -> {
        JsonRpcMessage<EstimateFeeResponse> result = objectMapper.readValue(response, new TypeReference<>() {
        });
        VoltaError error = result.getError();
        if (error != null) {
          throw new VoltaException(error.getMessage(), error.getCode());
        } else {
          return result.getResult();
        }
      });
  }

  @Override
  public Single<String> sendUserOperation(
    Blockchain blockchain,
    UserOperation operation,
    String entryPoint
  ) {
    return jsonRpc(blockchain, "eth_sendUserOperation", List.of(operation, entryPoint))
      .map(response -> {
        JsonRpcMessage<String> result = objectMapper.readValue(response, new TypeReference<>() {
        });
        VoltaError error = result.getError();
        if (error != null) {
          throw new VoltaException(error.getMessage(), error.getCode());
        } else {
          return result.getResult();
        }
      });
  }

  private Single<String> jsonRpc(Blockchain blockchain, String method, Object params) {
    return Single.create(emitter -> {
      String url = getUrl(blockchain);
      if (Utils.isNullOrBlank(url)) {
        emitter.onError(new IllegalArgumentException(String.format("no url for blockchain %s", blockchain)));
        return;
      }
      JsonRpcMessage<Object> request = new JsonRpcMessage<>(
        "2.0",
        method,
        params,
        1
      );
      RequestBody body = RequestBody.create(objectMapper.writeValueAsString(request), MediaType.get("application/json; charset=utf-8"));
      Request httpRequest = new Request.Builder()
        .url(url)
        .post(body)
        .build();
      Response httpResponse = client.newCall(httpRequest).execute();
      emitter.onSuccess(httpResponse.body().string());
    });
  }

  private String getUrl(Blockchain blockchain) {
    // TODO: return bundle url by blockchain
    return bundleServiceUrl;
  }
}
