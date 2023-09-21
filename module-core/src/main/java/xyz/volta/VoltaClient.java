package xyz.volta;

import xyz.volta.exception.VoltaException;
import xyz.volta.model.UserOperation;

public interface VoltaClient {
  UserOperation buildUserOperation(UserOperation operation) throws VoltaException;

  Object sendUserOperation(UserOperation operation) throws VoltaException;
}
