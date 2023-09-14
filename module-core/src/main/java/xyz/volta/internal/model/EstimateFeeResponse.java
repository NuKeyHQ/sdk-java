package xyz.volta.internal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class EstimateFeeResponse {
    private BigInteger preVerificationGas;
    private BigInteger verificationGas;
    private BigInteger callGasLimit;

    public BigInteger getPreVerificationGas() {
        return preVerificationGas;
    }

    public BigInteger getVerificationGas() {
        return verificationGas;
    }

    public BigInteger getCallGasLimit() {
        return callGasLimit;
    }
}
