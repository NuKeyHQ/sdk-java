package xyz.voltawallet;

import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.UserOperation;

public interface VoltaClient {
  Object send(final UserOperation operation) throws VoltaException;
}
