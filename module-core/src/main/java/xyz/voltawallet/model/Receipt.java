package xyz.voltawallet.model;

import java.util.List;

public class Receipt {
  private String blockHash;
  private String blockNumber;
  private String from;
  private String cumulativeGasUsed;
  private String gasUsed;
  private String logsBloom;
  private String transactionHash;
  private String transactionIndex;
  private String effectiveGasPrice;
  private List<Log> logs;

  public String getBlockHash() {
    return blockHash;
  }

  public void setBlockHash(String blockHash) {
    this.blockHash = blockHash;
  }

  public String getBlockNumber() {
    return blockNumber;
  }

  public void setBlockNumber(String blockNumber) {
    this.blockNumber = blockNumber;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getCumulativeGasUsed() {
    return cumulativeGasUsed;
  }

  public void setCumulativeGasUsed(String cumulativeGasUsed) {
    this.cumulativeGasUsed = cumulativeGasUsed;
  }

  public String getGasUsed() {
    return gasUsed;
  }

  public void setGasUsed(String gasUsed) {
    this.gasUsed = gasUsed;
  }

  public String getLogsBloom() {
    return logsBloom;
  }

  public void setLogsBloom(String logsBloom) {
    this.logsBloom = logsBloom;
  }

  public String getTransactionHash() {
    return transactionHash;
  }

  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }

  public String getTransactionIndex() {
    return transactionIndex;
  }

  public void setTransactionIndex(String transactionIndex) {
    this.transactionIndex = transactionIndex;
  }

  public String getEffectiveGasPrice() {
    return effectiveGasPrice;
  }

  public void setEffectiveGasPrice(String effectiveGasPrice) {
    this.effectiveGasPrice = effectiveGasPrice;
  }

  public List<Log> getLogs() {
    return logs;
  }

  public void setLogs(List<Log> logs) {
    this.logs = logs;
  }
}
