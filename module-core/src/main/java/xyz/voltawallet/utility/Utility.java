package xyz.voltawallet.utility;

import java.math.BigInteger;

public final class Utility {

  private static final int ADDRESS_BYTE_SIZE = 20;
  private static final String HEXADECIMAL_PATTERN = "^[\\dA-Fa-f]+$";

  public static boolean isHexAddress(final String address) {
    if (address == null || address.length() < 2 * ADDRESS_BYTE_SIZE) {
      return false;
    }
    String addressWithoutPadding = has0XPrefix(address) ? address.substring(2) : address;
    return addressWithoutPadding.length() == 2 * ADDRESS_BYTE_SIZE && addressWithoutPadding.matches(HEXADECIMAL_PATTERN);
  }

  public static boolean isHexData(final String text) {
    return has0XPrefix(text) && text.substring(2).matches(HEXADECIMAL_PATTERN);
  }

  public static boolean has0XPrefix(String text) {
    return text != null && text.length() >= 2 && (text.startsWith("0x") || text.startsWith("0X"));
  }

  public static boolean isNullOrBlank(String text) {
    return text == null || text.isBlank();
  }

  public static String toHexNumber(BigInteger number) {
    if (number != null) {
      return "0x" + number.toString(16);
    } else {
      return "0x";
    }
  }
}
