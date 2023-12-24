package xyz.voltawallet.model;

import xyz.voltawallet.utility.Utility;

import java.math.BigInteger;

public class Call {
  /**
   * Target is the address of the contract to call
   **/
  private String target;
  /**
   * Value is the amount of ETH to send with the call (in wei)
   **/
  private BigInteger value;
  /**
   * Data is the data to send with the call
   **/
  private byte[] data;

  public Call(String target, BigInteger value, byte[] data) {
    if (!Utility.isHexAddress(target)) throw new IllegalArgumentException("target must be a hex address");
    this.target = target;
    this.value = value;
    this.data = data;
  }

  public String getTarget() {
    return target;
  }

  public BigInteger getValue() {
    return value;
  }

  public byte[] getData() {
    return data != null ? data : new byte[0];
  }
}
