package xyz.voltawallet.model;

import xyz.voltawallet.utility.Utility;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class Vault {
  private final List<String> owners;
  private final BigInteger threshold;
  private final BigInteger seed;

  public Vault(List<String> owners, int threshold, BigInteger seed) {
    if (threshold < 1) {
      throw new IllegalArgumentException("Threshold must be greater than 0");
    }
    if (owners == null || owners.size() < threshold) {
      throw new IllegalArgumentException("Number of owner must be greater than threshold");
    }
    if (owners.stream().anyMatch(s -> !Utility.isHexAddress(s))) {
      throw new IllegalArgumentException("An owner address is invalid");
    }
    if (seed == null) {
      throw new IllegalArgumentException("No seed is provided");
    }
    this.owners = Collections.unmodifiableList(owners);
    this.threshold = BigInteger.valueOf(threshold);
    this.seed = seed;
  }

  public List<String> getOwners() {
    return owners;
  }

  public BigInteger getThreshold() {
    return threshold;
  }

  public BigInteger getSeed() {
    return seed;
  }
}
