package xyz.voltawallet.model;

import java.util.List;

public class UserOperationReceiptResponse {
  private String userOpHash;
  private String sender;
  private String paymaster;
  private String nonce;
  private boolean success;
  private String actualGasCost;
  private String actualGasUsed;
  private String from;
  private Receipt receipt;
  private List<Log> logs;

  public String getUserOpHash() {
    return userOpHash;
  }

  public String getSender() {
    return sender;
  }

  public String getPaymaster() {
    return paymaster;
  }

  public String getNonce() {
    return nonce;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getActualGasCost() {
    return actualGasCost;
  }

  public String getActualGasUsed() {
    return actualGasUsed;
  }

  public String getFrom() {
    return from;
  }

  public Receipt getReceipt() {
    return receipt;
  }

  public List<Log> getLogs() {
    return logs;
  }

  public void setUserOpHash(String userOpHash) {
    this.userOpHash = userOpHash;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setPaymaster(String paymaster) {
    this.paymaster = paymaster;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setActualGasCost(String actualGasCost) {
    this.actualGasCost = actualGasCost;
  }

  public void setActualGasUsed(String actualGasUsed) {
    this.actualGasUsed = actualGasUsed;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public void setReceipt(Receipt receipt) {
    this.receipt = receipt;
  }

  public void setLogs(List<Log> logs) {
    this.logs = logs;
  }
}
