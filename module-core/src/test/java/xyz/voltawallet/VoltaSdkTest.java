package xyz.voltawallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VoltaSdkTest {

  @Test
  public void nullUrl_MustThrow() {
    assertThrows(IllegalArgumentException.class, () -> VoltaSdk.newBundlerClient(null));
  }

  @Test
  public void emptyUrl_MustThrow() {
    assertThrows(IllegalArgumentException.class, () -> VoltaSdk.newBundlerClient(null));
  }
}
