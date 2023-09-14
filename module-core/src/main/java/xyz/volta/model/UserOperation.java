package xyz.volta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.web3j.protocol.core.methods.response.AbiDefinition;
import xyz.volta.consts.Blockchain;

import java.math.BigInteger;

public class UserOperation {
    private String sender;
    /**
     * Comes from the EntryPoint contract
     */
    private BigInteger nonce;
    /**
     * This is for creating a new wallet. SDK will not support this yet.
     */
    private String initCode;
    private String callData;
    /**
     * Gas allocated for execution step in handleOps
     */
    private BigInteger callGasLimit;
    /**
     * Gas allocated for verification step in handleOps
     */
    private BigInteger verificationGasLimit;
    /**
     * Gas allocated for other overhead in handleOps (and fee paid to bundler?)
     */
    private BigInteger preVerificationGas;
    private BigInteger maxFeePerGas;
    private BigInteger maxPriorityFeePerGas;
    private String paymasterAndData;
    private String signature;
    @JsonIgnore
    private String entryPointAddress;
    @JsonIgnore
    private Blockchain blockchain;

    private UserOperation(String sender,
                          BigInteger nonce,
                          String initCode,
                          String callData,
                          BigInteger callGasLimit,
                          BigInteger verificationGasLimit,
                          BigInteger preVerificationGas,
                          BigInteger maxFeePerGas,
                          BigInteger maxPriorityFeePerGas,
                          String paymasterAndData,
                          String signature,
                          String entryPointAddress,
                          Blockchain blockchain) {
        this.sender = sender;
        this.nonce = nonce;
        this.initCode = initCode;
        this.callData = callData;
        this.callGasLimit = callGasLimit;
        this.verificationGasLimit = verificationGasLimit;
        this.preVerificationGas = preVerificationGas;
        this.maxFeePerGas = maxFeePerGas;
        this.maxPriorityFeePerGas = maxPriorityFeePerGas;
        this.paymasterAndData = paymasterAndData;
        this.signature = signature;
        this.entryPointAddress = entryPointAddress;
        this.blockchain = blockchain;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder copyToBuilder() {
        return new Builder()
                .setSender(sender)
                .setNonce(nonce)
                .setInitCode(initCode)
                .setCallData(callData)
                .setCallGasLimit(callGasLimit)
                .setVerificationGasLimit(verificationGasLimit)
                .setPreVerificationGas(preVerificationGas)
                .setMaxFeePerGas(maxFeePerGas)
                .setMaxPriorityFeePerGas(maxPriorityFeePerGas)
                .setPaymasterAndData(paymasterAndData)
                .setSignature(signature)
                .setEntryPointAddress(entryPointAddress)
                .setBlockchain(blockchain);
    }

    public String getSender() {
        return sender;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public String getInitCode() {
        return initCode;
    }

    public String getCallData() {
        return callData;
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

    public String getPaymasterAndData() {
        return paymasterAndData;
    }

    public String getSignature() {
        return signature;
    }

    public String getEntryPointAddress() {
        return entryPointAddress;
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public static class Builder {

        private String sender;
        private BigInteger nonce;
        private String initCode;
        private String callData;
        private BigInteger callGasLimit;
        private BigInteger verificationGasLimit;
        private BigInteger preVerificationGas;
        private BigInteger maxFeePerGas;
        private BigInteger maxPriorityFeePerGas;
        private String paymasterAndData;
        private String signature;
        private String entryPointAddress;
        private Blockchain blockchain;

        private Builder() {
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setNonce(BigInteger nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder setInitCode(String initCode) {
            this.initCode = initCode;
            return this;
        }

        public Builder setCallData(String callData) {
            this.callData = callData;
            return this;
        }

        public Builder setCallGasLimit(BigInteger callGasLimit) {
            this.callGasLimit = callGasLimit;
            return this;
        }

        public Builder setVerificationGasLimit(BigInteger verificationGasLimit) {
            this.verificationGasLimit = verificationGasLimit;
            return this;
        }

        public Builder setPreVerificationGas(BigInteger preVerificationGas) {
            this.preVerificationGas = preVerificationGas;
            return this;
        }

        public Builder setMaxFeePerGas(BigInteger maxFeePerGas) {
            this.maxFeePerGas = maxFeePerGas;
            return this;
        }

        public Builder setMaxPriorityFeePerGas(BigInteger maxPriorityFeePerGas) {
            this.maxPriorityFeePerGas = maxPriorityFeePerGas;
            return this;
        }

        public Builder setPaymasterAndData(String paymasterAndData) {
            this.paymasterAndData = paymasterAndData;
            return this;
        }

        public Builder setSignature(String signature) {
            this.signature = signature;
            return this;
        }

        public Builder setEntryPointAddress(String entryPointAddress) {
            this.entryPointAddress = entryPointAddress;
            return this;
        }

        public Builder setBlockchain(Blockchain blockchain) {
            this.blockchain = blockchain;
            return this;
        }

        public UserOperation build() {
            return new UserOperation(sender,
                    nonce,
                    initCode,
                    callData,
                    callGasLimit,
                    verificationGasLimit,
                    preVerificationGas,
                    maxFeePerGas,
                    maxPriorityFeePerGas,
                    paymasterAndData,
                    signature,
                    entryPointAddress,
                    blockchain
            );
        }
    }
}
