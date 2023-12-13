package xyz.voltawallet.internal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import xyz.voltawallet.internal.jackson.BigIntHexDeserialize;
import xyz.voltawallet.internal.jackson.BigIntHexSerialize;

import java.math.BigInteger;

public class BlockInfoResponse {

  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger baseFeePerGas;

  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger difficulty;

  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger gasLimit;

  @JsonDeserialize(using = BigIntHexDeserialize.class)
  @JsonSerialize(using = BigIntHexSerialize.class)
  private BigInteger gasUsed;

  public BlockInfoResponse() {
  }

  public BigInteger getBaseFeePerGas() {
    return baseFeePerGas;
  }

  public BigInteger getDifficulty() {
    return difficulty;
  }

  public BigInteger getGasLimit() {
    return gasLimit;
  }

  public BigInteger getGasUsed() {
    return gasUsed;
  }
}
