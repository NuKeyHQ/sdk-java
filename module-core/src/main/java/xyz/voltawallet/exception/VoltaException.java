package xyz.voltawallet.exception;

public final class VoltaException extends Exception {

  private final int code;

  public VoltaException(final String message, final int code) {
    super(message);
    this.code = code;
  }

  public VoltaException(final String message) {
    this(message, -1);
  }
}
