package xyz.voltawallet.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilityTest {

  @Test
  public void testIsHexAddress_ValidHexAddress() {
    assertTrue(Utility.isHexAddress("0xabcdef0123456789abcdef0123456789abcdef01"));
    assertTrue(Utility.isHexAddress("0Xabcdef0123456789abcdef0123456789abcdef01"));
  }

  @Test
  public void testIsHexAddress_InvalidHexAddress() {
    assertFalse(Utility.isHexAddress(null));
    assertFalse(Utility.isHexAddress(""));
    assertFalse(Utility.isHexAddress("0x"));
    assertFalse(Utility.isHexAddress("123456789abcdef0123456789abcdef01234567")); // Missing "0x" prefix
    assertFalse(Utility.isHexAddress("0x123456789abcdef0123456789abcdef012345678aaaa")); // Length not equal to 40
    assertFalse(Utility.isHexAddress("0xghijklmnopqrstuvwxyz1234567890")); // Contains non-hex characters
  }

  @Test
  public void testIsHexData_ValidHexData() {
    assertTrue(Utility.isHexData("0xabcdef0123456789abcdef0123456789abcdef01"));
    assertTrue(Utility.isHexData("0Xabcdef0123456789abcdef0123456789abcdef01"));
  }

  @Test
  public void testIsHexData_InvalidHexData() {
    assertFalse(Utility.isHexData(null));
    assertFalse(Utility.isHexData(""));
    assertFalse(Utility.isHexData("0x"));
    assertFalse(Utility.isHexData("123456789abcdef0123456789abcdef01234567")); // Missing "0x" prefix
    assertFalse(Utility.isHexData("0xghijklmnopqrstuvwxyz1234567890")); // Contains non-hex characters
  }

  @Test
  public void testHas0XPrefix() {
    assertTrue(Utility.has0XPrefix("0xabcdef"));
    assertTrue(Utility.has0XPrefix("0Xabcdef"));
    assertFalse(Utility.has0XPrefix(null));
    assertFalse(Utility.has0XPrefix(""));
    assertFalse(Utility.has0XPrefix("abcdef"));
    assertFalse(Utility.has0XPrefix("0"));
  }

  @Test
  public void testIsNullOrBlank() {
    assertTrue(Utility.isNullOrBlank(null));
    assertTrue(Utility.isNullOrBlank(""));
    assertTrue(Utility.isNullOrBlank("   "));
    assertFalse(Utility.isNullOrBlank("abc"));
  }
}
