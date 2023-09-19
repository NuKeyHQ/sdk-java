package xyz.volta.internal;

import io.reactivex.Single;
import xyz.volta.constant.Blockchain;
import xyz.volta.internal.model.EstimateFeeResponse;
import xyz.volta.model.UserOperation;

interface BundleClient {
  Single<EstimateFeeResponse> estimateUserOperationGas(Blockchain blockchain, UserOperation userOperation, String entryPoint);
  Single<String> sendUserOperation(Blockchain blockchain, UserOperation userOp, String entryPoint);
}
