package xyz.voltawallet.internal;

import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.internal.model.BlockInfoResponse;
import xyz.voltawallet.internal.model.EstimateFeeResponse;
import xyz.voltawallet.model.UserOperation;

import java.io.IOException;
import java.math.BigInteger;

interface BundleClient {
  /**
   * Estimate the gas required for a user operation.
   *
   * @param operation  The user operation to estimate the gas for.
   * @param entryPoint The entry point to estimate the gas for.
   */
  EstimateFeeResponse estimateUserOperationGas(final UserOperation operation, final String entryPoint) throws IOException, VoltaException;

  /**
   * Send a user operation to the blockchain.
   *
   * @param operation  The user operation to send.
   * @param entryPoint The entry point to send the user operation to.
   */
  String sendUserOperation(final UserOperation operation, final String entryPoint) throws IOException, VoltaException;

  BigInteger suggestGasTipCap() throws IOException, VoltaException;

  BlockInfoResponse lastBlockInfo() throws IOException, VoltaException;
}
