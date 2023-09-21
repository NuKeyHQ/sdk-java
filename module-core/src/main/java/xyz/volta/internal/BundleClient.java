package xyz.volta.internal;

import xyz.volta.constant.Blockchain;
import xyz.volta.internal.model.EstimateFeeResponse;
import xyz.volta.model.UserOperation;

import java.io.IOException;

interface BundleClient {
  /**
   * Estimate the gas required for a user operation.
   *
   * @param blockchain The blockchain to estimate the gas for.
   * @param operation  The user operation to estimate the gas for.
   * @param entryPoint The entry point to estimate the gas for.
   */
  EstimateFeeResponse estimateUserOperationGas(final Blockchain blockchain, final UserOperation operation, final String entryPoint) throws IOException;

  /**
   * Send a user operation to the blockchain.
   *
   * @param blockchain The blockchain to send the user operation to.
   * @param operation  The user operation to send.
   * @param entryPoint The entry point to send the user operation to.
   */
  Object sendUserOperation(final Blockchain blockchain, final UserOperation operation, final String entryPoint) throws IOException;
}
