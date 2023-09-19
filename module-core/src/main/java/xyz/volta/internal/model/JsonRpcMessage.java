package xyz.volta.internal.model;

public class JsonRpcMessage<R> {
  private String jsonrpc;
  private String method;
  private Object params;
  private R result;
  private VoltaError error;
  private int id;

  public JsonRpcMessage(String jsonrpc, String method, Object params, int id) {
    this.jsonrpc = jsonrpc;
    this.method = method;
    this.params = params;
    this.id = id;
  }

  public JsonRpcMessage() {
  }

  public String getJsonrpc() {
    return jsonrpc;
  }

  public String getMethod() {
    return method;
  }

  public Object getParams() {
    return params;
  }

  public R getResult() {
    return result;
  }

  public VoltaError getError() {
    return error;
  }

  public int getId() {
    return id;
  }
}
