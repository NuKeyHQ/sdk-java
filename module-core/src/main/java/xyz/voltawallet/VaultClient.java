package xyz.voltawallet;

import org.web3j.protocol.core.methods.request.Transaction;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public interface VaultClient {
  BigInteger nextNonce(String senderAddress);

  UserOperation buildExecuteUserOp(Vault vault, Call call) throws IOException, VoltaException;

  UserOperation buildExecuteUserOpFromTx(Vault vault, Transaction tx) throws IOException, VoltaException;

  UserOperation buildExecuteBatchUserOp(Vault vault, List<Call> calls) throws IllegalArgumentException, IOException, VoltaException;

  UserOperation buildCustomUserOp(Vault vault, byte[] callData) throws IllegalArgumentException, IOException, VoltaException;

  UserOperation suggestUserOpGasPrice(UserOperation userOperation) throws IOException, VoltaException;
}
