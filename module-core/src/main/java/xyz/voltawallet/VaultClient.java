package xyz.voltawallet;

import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.UserOperation;
import xyz.voltawallet.model.Vault;

import java.io.IOException;
import java.math.BigInteger;

public interface VaultClient {
  BigInteger nextNonce(String senderAddress);
  UserOperation buildExecuteUserOp(Vault vault, Call call) throws IOException, VoltaException;
}
