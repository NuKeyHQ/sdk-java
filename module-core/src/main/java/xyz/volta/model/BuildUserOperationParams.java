package xyz.volta.model;

import xyz.volta.consts.Blockchain;

import java.math.BigInteger;

public class BuildUserOperationParams {
    /**
     * Sender is the address of the smart contract wallet
     */
    private String sender;
    /**
     * CallData is the (hex-encoded) data that will be sent to/executed on the smart contract wallet
     * This should be a call to `execute(to, value, data)`
     */
    private String callData;
    /**
     * Blockchain is the blockchain that the smart contract wallet is on
     */
    private Blockchain blockchain;
    /**
     * EntryPointAddress is the address of the EntryPoint contract to use. If this is empty, the SDK will
     * use the default EntryPoint address for the given blockchain.
     */
    private String entryPointAddress;

    private BigInteger callGasLimit;
    private BigInteger verificationGasLimit;
    private BigInteger preVerificationGas;
    private BigInteger maxFeePerGas;
    private BigInteger maxPriorityFeePerGas;

    public String getSender() {
        return sender;
    }

    public String getCallData() {
        return callData;
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public String getEntryPointAddress() {
        return entryPointAddress;
    }

    public BigInteger getCallGasLimit() {
        return callGasLimit;
    }

    public BigInteger getVerificationGasLimit() {
        return verificationGasLimit;
    }

    public BigInteger getPreVerificationGas() {
        return preVerificationGas;
    }

    public BigInteger getMaxFeePerGas() {
        return maxFeePerGas;
    }

    public BigInteger getMaxPriorityFeePerGas() {
        return maxPriorityFeePerGas;
    }
}
