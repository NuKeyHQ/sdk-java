package xyz.volta.internal.model;

public class JsonRpcMessage {
    private String jsonrpc;
    private String method;
    private Object params;
    private byte[] result;
    private byte[] error;
    private int id;

    public JsonRpcMessage(String jsonrpc, String method, Object params, int id) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
        this.id = id;
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

    public byte[] getResult() {
        return result;
    }

    public byte[] getError() {
        return error;
    }

    public int getId() {
        return id;
    }
}
