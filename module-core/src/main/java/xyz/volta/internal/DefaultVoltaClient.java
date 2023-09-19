package xyz.volta.internal;

import io.reactivex.Single;
import xyz.volta.VoltaClient;
import xyz.volta.constant.Blockchain;
import xyz.volta.model.UserOperation;
import xyz.volta.utility.Utility;

import java.math.BigInteger;

class DefaultVoltaClient implements VoltaClient {

  private static final String ERR_INVALID_SENDER_ADDRESS = "invalid Sender address";
  private static final String ERR_INVALID_CALL_DATA = "invalid call data";
  private static final String ERR_INVALID_BLOCKCHAIN = "invalid blockchain";
  private static final String ERR_INVALID_ENTRY_POINT_ADDRESS = "invalid entrypoint address";
  private static final String ERR_INVALID_CALL_GAS_LIMIT = "invalid call gas limit";
  private static final String ERR_INVALID_VERIFICATION_GAS_LIMIT = "invalid verification gas limit";
  private static final String ERR_INVALID_PRE_VERIFICATION_GAS = "invalid pre verification gas";
  private static final String ERR_INVALID_MAX_FEE_PER_GAS = "invalid max fee per gas";
  private static final String ERR_INVALID_MAX_PRIORITY_FEE_PER_GAS = "invalid max priority fee per gas";

  private final BundleClient bundleClient;

  DefaultVoltaClient(BundleClient bundleClient) {
    this.bundleClient = bundleClient;
  }

  @Override
  public Single<UserOperation> buildUserOperation(UserOperation operation) {
    String error = validateParams(operation);
    if (error != null) {
      return Single.error(new IllegalArgumentException(error));
    }
    String address = operation.getEntryPointAddress();
    if (address == null || address.isBlank()) {
      address = Blockchain.defaultEntryPointAddress();
    }

    // TODO: get nonce
    UserOperation userOperation = operation.copyToBuilder()
      .setEntryPointAddress(address)
      .build();

    return bundleClient.estimateUserOperationGas(operation.getBlockchain(), userOperation, address)
      .map(it -> userOperation.copyToBuilder()
        .setPreVerificationGas(it.getPreVerificationGas())
        .setVerificationGasLimit(it.getVerificationGas())
        .setCallGasLimit(it.getCallGasLimit())
        .build());
  }

  private String validateParams(UserOperation params) {
    if (!Utility.isHexAddress(params.getSender())) {
      return ERR_INVALID_SENDER_ADDRESS;
    }

    if (!Utility.isHexData(params.getCallData())) {
      return ERR_INVALID_CALL_DATA;
    }

    if (params.getBlockchain() == null) {
      return ERR_INVALID_BLOCKCHAIN;
    }
    if (params.getEntryPointAddress() != null && !params.getEntryPointAddress().isBlank() && !Utility.isHexAddress(params.getEntryPointAddress())) {
      return ERR_INVALID_ENTRY_POINT_ADDRESS;
    }

    // If any gas values are set to negative, return an error
    if (params.getCallGasLimit() != null && params.getCallGasLimit().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_CALL_GAS_LIMIT;
    }
    if (params.getVerificationGasLimit() != null && params.getVerificationGasLimit().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_VERIFICATION_GAS_LIMIT;
    }
    if (params.getPreVerificationGas() != null && params.getPreVerificationGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_PRE_VERIFICATION_GAS;
    }
    if (params.getMaxFeePerGas() != null && params.getMaxFeePerGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_MAX_FEE_PER_GAS;
    }
    if (params.getMaxPriorityFeePerGas() != null && params.getMaxPriorityFeePerGas().compareTo(BigInteger.ZERO) < 0) {
      return ERR_INVALID_MAX_PRIORITY_FEE_PER_GAS;
    }

    return null;
  }

  @Override
  public Single<String> sendUserOperation(UserOperation operation) {
    String error = null;
    if (operation == null) {
      error = "Input param is null";
    } else if (operation.getBlockchain() == null) {
      error = "Blockchain param must be not null";
    } else if (!Utility.isHexAddress(operation.getEntryPointAddress())) {
      error = "Invalid entry point address";
    } else if (Utility.isNullOrBlank(operation.getSignature())) {
      error = "User operation must be signed before sending";
    }
    if (error != null) {
      return Single.error(new IllegalArgumentException(error));
    }
    return bundleClient.sendUserOperation(operation.getBlockchain(), operation, operation.getEntryPointAddress());
  }
}
