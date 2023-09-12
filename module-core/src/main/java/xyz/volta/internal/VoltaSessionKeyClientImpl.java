package xyz.volta.internal;

import io.reactivex.Single;
import xyz.volta.VoltaSessionKeyClient;
import xyz.volta.consts.Blockchain;
import xyz.volta.model.BuildUserOperationParams;
import xyz.volta.model.UserOperation;
import xyz.volta.utils.Utils;

import java.math.BigInteger;

class VoltaSessionKeyClientImpl implements VoltaSessionKeyClient {

    private static final String ERR_INVALID_SENDER_ADDRESS = "invalid Sender address";
    private static final String ERR_INVALID_CALL_DATA = "invalid call data";
    private static final String ERR_INVALID_BLOCKCHAIN = "invalid blockchain";
    private static final String ERR_INVALID_ENTRY_POINT_ADDRESS = "invalid entrypoint address";
    private static final String ERR_INVALID_CALL_GAS_LIMIT = "invalid call gas limit";
    private static final String ERR_INVALID_VERIFICATION_GAS_LIMIT = "invalid verification gas limit";
    private static final String ERR_INVALID_PRE_VERIFICATION_GAS = "invalid pre verification gas";
    private static final String ERR_INVALID_MAX_FEE_PER_GAS = "invalid max fee per gas";
    private static final String ERR_INVALID_MAX_PRIORITY_FEE_PER_GAS = "invalid max priority fee per gas";

    @Override
    public Single<UserOperation> buildUserOperation(BuildUserOperationParams params) {
        String errorMsg = validateParams(params);
        if (errorMsg != null) {
            return Single.error(new IllegalArgumentException(errorMsg));
        }
        String entryPointAddress = params.getEntryPointAddress();
        if (entryPointAddress == null || entryPointAddress.isBlank()) {
            entryPointAddress = Blockchain.defaultEntryPointAddress();
        }

        // TODO: get nonce and estimate user operation gas
        UserOperation userOperation = UserOperation.builder()
                .setSender(params.getSender())
                .setCallData(params.getCallData())
                .setBlockchain(params.getBlockchain())
                .setEntryPointAddress(entryPointAddress)
                .build();

        return Single.just(userOperation);
    }

    private String validateParams(BuildUserOperationParams params) {
        if (!Utils.isHexAddress(params.getSender())) {
            return ERR_INVALID_SENDER_ADDRESS;
        }

        if (!Utils.isHexData(params.getCallData())) {
            return ERR_INVALID_CALL_DATA;
        }

        if (params.getBlockchain() == null) {
            return ERR_INVALID_BLOCKCHAIN;
        }
        if (params.getEntryPointAddress() != null && !params.getEntryPointAddress().isBlank() && !Utils.isHexAddress(params.getEntryPointAddress())) {
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
}
