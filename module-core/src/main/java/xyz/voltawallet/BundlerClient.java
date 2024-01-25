package xyz.voltawallet;

import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.EstimateFeeResponse;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.UserOperationReceiptResponse;

import java.io.IOException;

public interface BundlerClient {
  /**
   * Estimate the gas required for a user operation.
   *
   * @param operation The user operation to estimate the gas for.
   */
  EstimateFeeResponse estimateUserOperationGas(final UserOperation operation) throws IOException, VoltaException;

  /**
   * Send a user operation to the blockchain.
   *
   * @param operation The user operation to send.
   */
  String sendUserOperation(final UserOperation operation) throws IOException, VoltaException;


  /**
   * Get receipt of User Operation
   *
   * @param userOpHash the User Operation hash
   */
  UserOperationReceiptResponse getUserOperationReceipt(final String userOpHash) throws IOException, VoltaException;
}
