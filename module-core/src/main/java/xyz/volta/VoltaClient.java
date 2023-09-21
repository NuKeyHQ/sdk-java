package xyz.volta;

import xyz.volta.exception.VoltaException;
import xyz.volta.model.UserOperation;

public interface VoltaClient {
  UserOperation build(final UserOperation operation) throws VoltaException;
  Object send(final UserOperation operation) throws VoltaException;
}
