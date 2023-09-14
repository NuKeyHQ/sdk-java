package xyz.volta.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import xyz.volta.consts.Blockchain;
import xyz.volta.internal.model.EstimateFeeResponse;
import xyz.volta.internal.model.JsonRpcMessage;
import xyz.volta.model.UserOperation;
import xyz.volta.utils.Utils;

import java.util.List;

class BundleApiClientImpl implements BundleApiClient {

    private final ObjectMapper objectMapper;

    private final OkHttpClient client;

    BundleApiClientImpl(ObjectMapper objectMapper, OkHttpClient client) {
        this.objectMapper = objectMapper;
        this.client = client;
    }

    @Override
    public Single<EstimateFeeResponse> estimateUserOperationGas(Blockchain blockchain, UserOperation userOperation, String entryPoint) {
        return doJSONRPCRequest(blockchain, "eth_estimateUserOperationGas", List.of(userOperation, entryPoint))
                .map(jsonRpcMessage -> objectMapper.readValue(jsonRpcMessage.getResult(), EstimateFeeResponse.class));
    }

    @Override
    public Single<String> sendUserOperation(Blockchain blockchain, UserOperation userOp, String entryPoint) {
        return doJSONRPCRequest(blockchain, "eth_sendUserOperation", List.of(userOp, entryPoint))
                .map(jsonRpcMessage -> objectMapper.readValue(jsonRpcMessage.getResult(), String.class));
    }

    private Single<JsonRpcMessage> doJSONRPCRequest(Blockchain blockchain, String method, Object params) {
        return Single.create(emitter -> {
            String url = getUrl(blockchain);
            if (Utils.isNullOrBlank(url)) {
                emitter.onError(new IllegalArgumentException(String.format("no url for blockchain %s", blockchain)));
                return;
            }
            JsonRpcMessage request = new JsonRpcMessage(
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
            emitter.onSuccess(objectMapper.readValue(httpResponse.body().string(), JsonRpcMessage.class));
        });
    }

    private String getUrl(Blockchain blockchain) {
        // TODO: return bundle url by blockchain
        return "https://example.com";
    }
}
