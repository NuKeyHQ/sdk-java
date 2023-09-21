package xyz.volta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoltaSdkTest {

  @Test
  public void clientForNullMustThrow() {
    assertThrows(IllegalArgumentException.class, () -> VoltaSdk.clientFor(null));
  }

  @Test
  public void clientForEmptyMustThrow() {
    assertThrows(IllegalArgumentException.class, () -> VoltaSdk.clientFor(null));
  }
}
