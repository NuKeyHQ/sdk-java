package xyz.volta;

import io.reactivex.Single;
import xyz.volta.model.UserOperation;

public interface VoltaClient {
  Single<UserOperation> buildUserOperation(UserOperation operation);
  Single<String> sendUserOperation(UserOperation operation);
}
