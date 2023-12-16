package xyz.voltawallet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Log {
  private String address;
  private List<String> topics;
  private String data;
  private String blockNumber;
  private String transactionHash;
  private String transactionIndex;
  private String blockHash;
  private String logIndex;
  private boolean removed;

  public String getAddress() {
    return address;
  }

  public List<String> getTopics() {
    return topics;
  }

  public String getData() {
    return data;
  }

  public String getBlockNumber() {
    return blockNumber;
  }

  public String getTransactionHash() {
    return transactionHash;
  }

  public String getTransactionIndex() {
    return transactionIndex;
  }

  public String getBlockHash() {
    return blockHash;
  }

  public String getLogIndex() {
    return logIndex;
  }

  public boolean isRemoved() {
    return removed;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setTopics(List<String> topics) {
    this.topics = topics;
  }

  public void setData(String data) {
    this.data = data;
  }

  public void setBlockNumber(String blockNumber) {
    this.blockNumber = blockNumber;
  }

  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }

  public void setTransactionIndex(String transactionIndex) {
    this.transactionIndex = transactionIndex;
  }

  public void setBlockHash(String blockHash) {
    this.blockHash = blockHash;
  }

  public void setLogIndex(String logIndex) {
    this.logIndex = logIndex;
  }

  public void setRemoved(boolean removed) {
    this.removed = removed;
  }
}
