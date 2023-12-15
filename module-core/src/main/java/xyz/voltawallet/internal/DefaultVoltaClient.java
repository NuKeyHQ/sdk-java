package xyz.voltawallet.internal;

import xyz.voltawallet.VoltaClient;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.utility.Utility;

import java.io.IOException;

final class DefaultVoltaClient implements VoltaClient {

  private final BundleClient bundleClient;

  DefaultVoltaClient(final BundleClient bundleClient) {
    this.bundleClient = bundleClient;
  }

  @Override
  public Object send(final UserOperation operation) throws VoltaException {
    final String error;
    if (operation == null) {
      error = "Input param is null";
    } else if (Utility.isNullOrBlank(operation.getSignature())) {
      error = "User operation must be signed before sending";
    } else {
      error = null;
    }
    if (error != null) {
      throw new VoltaException(error);
    }
    try {
      return bundleClient.sendUserOperation(operation, operation.getEntryPointAddress());
    } catch (IOException e) {
      throw new VoltaException(e.getMessage());
    }
  }
}
