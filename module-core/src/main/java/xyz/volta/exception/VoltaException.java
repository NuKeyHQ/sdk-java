package xyz.volta.exception;

public class VoltaException extends Exception {
  private int code;

  public VoltaException(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
