package xyz.voltawallet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;
import xyz.voltawallet.internal.jackson.BigIntHexDeserialize;
import xyz.voltawallet.internal.jackson.BigIntHexSerialize;
import xyz.voltawallet.utility.Utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class UserOperation {
  private String sender;
  /**
   * Comes from the EntryPoint contract
   */
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger nonce;
  /**
   * This is for creating a new wallet. SDK will not support this yet.
   */
  private String initCode;
  private String callData;
  /**
   * Gas allocated for execution step in handleOps
   */
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger callGasLimit;
  /**
   * Gas allocated for verification step in handleOps
   */
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger verificationGasLimit;
  /**
   * Gas allocated for other overhead in handleOps (and fee paid to bundler?)
   */
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger preVerificationGas;
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger maxFeePerGas;
  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger maxPriorityFeePerGas;
  private String paymasterAndData;
  private String signature;
  @JsonIgnore
  private String entryPointAddress;
  @JsonIgnore
  private long chainId;

  public UserOperation() {
  }

  private UserOperation(
    final String sender,
    final BigInteger nonce,
    final String initCode,
    final String callData,
    final BigInteger callGasLimit,
    final BigInteger verificationGasLimit,
    final BigInteger preVerificationGas,
    final BigInteger maxFeePerGas,
    final BigInteger maxPriorityFeePerGas,
    final String paymasterAndData,
    final String signature,
    final String entryPointAddress,
    final long chainId
  ) {
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
    this.chainId = chainId;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String sign(String... privateKeys) {
    if (privateKeys == null || privateKeys.length == 0) throw new IllegalArgumentException("No Private key is set");
    String usrOpHash = getUserOperationHash();
    String message = Numeric.toHexString("\u0019Ethereum Signed Message:\n32".getBytes(StandardCharsets.UTF_8));
    String digest = Hash.sha3(message + Numeric.cleanHexPrefix(usrOpHash));

    StringBuilder signatureBuilder = new StringBuilder("0x");
    for (String privateKey : privateKeys) {
      Credentials credentials = Credentials.create(privateKey);
      Sign.SignatureData sig = Sign.signMessage(Numeric.hexStringToByteArray(digest), credentials.getEcKeyPair(), false);
      signatureBuilder.append(Numeric.cleanHexPrefix(Numeric.toHexString(sig.getR())));
      signatureBuilder.append(Numeric.cleanHexPrefix(Numeric.toHexString(sig.getS())));
      signatureBuilder.append(Numeric.cleanHexPrefix(Numeric.toHexString(sig.getV())));
    }
    signature = signatureBuilder.toString();
    return signature;
  }

  private String getUserOperationHash() {
    String packedData = packedForSignature();
    String hash = Hash.sha3(packedData);
    DynamicStruct packedStruct = new DynamicStruct(
      new Bytes32(Numeric.hexStringToByteArray(hash)),
      new Address(getEntryPointAddress()),
      new Uint256(chainId)
    );
    return Hash.sha3(TypeEncoder.encode(packedStruct));
  }

  private String packedForSignature() {
    DynamicStruct struct = new DynamicStruct(
      new Address(sender),
      new Uint256(nonce),
      new Bytes32(Hash.sha3(Numeric.hexStringToByteArray(initCode))),
      new Bytes32(Hash.sha3(Numeric.hexStringToByteArray(callData))),
      new Uint256(callGasLimit),
      new Uint256(verificationGasLimit),
      new Uint256(preVerificationGas),
      new Uint256(maxFeePerGas),
      new Uint256(maxPriorityFeePerGas),
      new Bytes32(Hash.sha3(Numeric.hexStringToByteArray(paymasterAndData)))
    );
    return TypeEncoder.encode(struct);
  }

  public Builder buildUpon() {
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
      .setChainId(chainId);
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
    return Utility.isHexAddress(entryPointAddress) ? entryPointAddress : ContractAddressesConfig.DEFAULT_ENTRY_POINT_ADDRESS;
  }

  @Override
  public String toString() {
    return String.format("""
        Sender:               %s,
        Nonce:                %s,
        InitCode:             %s,
        CallData:             %s,
        CallGasLimit:         %s,
        VerificationGasLimit: %s,
        PreVerificationGas:   %s,
        MaxFeePerGas:         %s,
        MaxPriorityFeePerGas: %s,
        PaymasterAndData:     %s,
        Signature:            %s,
        ChainId:              %d
        """, sender,
      Utility.toHexNumber(nonce),
      initCode,
      callData,
      Utility.toHexNumber(callGasLimit),
      Utility.toHexNumber(verificationGasLimit),
      Utility.toHexNumber(preVerificationGas),
      Utility.toHexNumber(maxFeePerGas),
      Utility.toHexNumber(maxPriorityFeePerGas),
      paymasterAndData,
      signature,
      chainId
    );
  }

  public static class Builder {

    private String sender;
    private BigInteger nonce;
    private String initCode = "0x";
    private String callData = "0x";
    private BigInteger callGasLimit;
    private BigInteger verificationGasLimit;
    private BigInteger preVerificationGas;
    private BigInteger maxFeePerGas;
    private BigInteger maxPriorityFeePerGas;
    private String paymasterAndData = "0x";
    private String signature = "0x";
    private String entryPointAddress;
    private long chainId;

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

    public Builder setChainId(long chainId) {
      this.chainId = chainId;
      return this;
    }

    public UserOperation build() {
      return new UserOperation(
        sender,
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
        chainId
      );
    }
  }
}
