package xyz.volta;

import io.reactivex.Single;
import xyz.volta.model.UserOperation;

public interface VoltaSessionKeyClient {
  Single<UserOperation> buildUserOperation(UserOperation params);

  Single<String> sendUserOperation(UserOperation userOperation);
}
