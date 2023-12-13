package xyz.voltawallet.internal;

import xyz.voltawallet.VoltaClient;
import xyz.voltawallet.constant.Blockchain;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.internal.model.EstimateFeeResponse;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.utility.Utility;

import java.io.IOException;
import java.math.BigInteger;

final class DefaultVoltaClient implements VoltaClient {

  private static final String ERR_NULL_USER_OPERATION = "invalid user operation";
  private static final String ERR_INVALID_SENDER_ADDRESS = "invalid sender address";
  private static final String ERR_INVALID_CALL_DATA = "invalid call data";
  private static final String ERR_INVALID_BLOCKCHAIN = "invalid blockchain";
  private static final String ERR_INVALID_ENTRY_POINT_ADDRESS = "invalid entrypoint address";
  private static final String ERR_INVALID_CALL_GAS_LIMIT = "invalid call gas limit";
  private static final String ERR_INVALID_VERIFICATION_GAS_LIMIT = "invalid verification gas limit";
  private static final String ERR_INVALID_PRE_VERIFICATION_GAS = "invalid pre verification gas";
  private static final String ERR_INVALID_MAX_FEE_PER_GAS = "invalid max fee per gas";
  private static final String ERR_INVALID_MAX_PRIORITY_FEE_PER_GAS = "invalid max priority fee per gas";

  private final BundleClient bundleClient;

  DefaultVoltaClient(final BundleClient bundleClient) {
    this.bundleClient = bundleClient;
  }

  private static String validate(UserOperation operation) {
    if (operation == null) {
      return ERR_NULL_USER_OPERATION;
    }

    if (!Utility.isHexAddress(operation.getSender())) {
      return ERR_INVALID_SENDER_ADDRESS;
    }

    if (!Utility.isHexData(operation.getCallData())) {
      return ERR_INVALID_CALL_DATA;
    }

    if (operation.getBlockchain() == null) {
      return ERR_INVALID_BLOCKCHAIN;
    }
    if (operation.getEntryPointAddress() != null && !operation.getEntryPointAddress().isBlank() && !Utility.isHexAddress(operation.getEntryPointAddress())) {
      return ERR_INVALID_ENTRY_POINT_ADDRESS;
    }

    // If any gas values are set to negative, return an error
    if (operation.getCallGasLimit() != null && operation.getCallGasLimit().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_CALL_GAS_LIMIT;
    }
    if (operation.getVerificationGasLimit() != null && operation.getVerificationGasLimit().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_VERIFICATION_GAS_LIMIT;
    }
    if (operation.getPreVerificationGas() != null && operation.getPreVerificationGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_PRE_VERIFICATION_GAS;
    }
    if (operation.getMaxFeePerGas() != null && operation.getMaxFeePerGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_MAX_FEE_PER_GAS;
    }
    if (operation.getMaxPriorityFeePerGas() != null && operation.getMaxPriorityFeePerGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_MAX_PRIORITY_FEE_PER_GAS;
    }

    return null;
  }

  @Override
  public UserOperation build(final UserOperation operation) throws VoltaException {
    String error = validate(operation);
    if (error != null) {
      throw new VoltaException(error);
    }
    String address = operation.getEntryPointAddress();
    if (address == null || address.isBlank()) {
      address = Blockchain.DEFAULT_ENTRY_POINT_ADDRESS;
    }

    // TODO: get nonce
    final UserOperation userOperation = operation.copyToBuilder()
      .setEntryPointAddress(address)
      .build();

    try {
      final EstimateFeeResponse response = bundleClient.estimateUserOperationGas(
        userOperation,
        address
      );
      return userOperation.copyToBuilder()
        .setPreVerificationGas(response.getPreVerificationGas())
        .setVerificationGasLimit(response.getVerificationGas())
        .setCallGasLimit(response.getCallGasLimit())
        .build();
    } catch (IOException e) {
      throw new VoltaException(e.getMessage());
    }
  }

  @Override
  public Object send(final UserOperation operation) throws VoltaException {
    final String error;
    if (operation == null) {
      error = "Input param is null";
    } else if (operation.getBlockchain() == null) {
      error = "Blockchain param must be not null";
    } else if (!Utility.isHexAddress(operation.getEntryPointAddress())) {
      error = "Invalid entry point address";
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
