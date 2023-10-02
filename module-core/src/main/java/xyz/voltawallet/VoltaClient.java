package xyz.voltawallet;

import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.UserOperation;

public interface VoltaClient {
  UserOperation build(final UserOperation operation) throws VoltaException;
  Object send(final UserOperation operation) throws VoltaException;
}
