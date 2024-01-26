package xyz.voltawallet;

import org.web3j.protocol.core.methods.request.Transaction;
import xyz.voltawallet.exception.VoltaException;
import xyz.voltawallet.model.Call;
import xyz.voltawallet.model.UserOperation;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public interface VaultClient {

  /**
   * Get next nonce of a Vault address
   *
   * @param senderAddress the vault address
   */
  BigInteger nextNonce(String senderAddress);


  /**
   * Build a User Operation to execute a {@link Call} from a sender
   *
   * @param sender the sender address
   * @param call   the {@link Call} request
   */
  UserOperation buildExecuteUserOperation(String sender, Call call) throws IOException, VoltaException;

  /**
   * Build a User Operation to execute a {@link Transaction} from a sender
   *
   * @param sender the sender address
   * @param tx     the {@link Transaction} request
   */
  UserOperation buildExecuteUserOperationFromTx(String sender, Transaction tx) throws IOException, VoltaException;

  /**
   * Build a User Operation to execute a batch of {@link Call} from a sender
   *
   * @param sender the sender address
   * @param calls  the list of {@link Call} request
   */
  UserOperation buildExecuteBatchUserOperation(String sender, List<Call> calls) throws IllegalArgumentException, IOException, VoltaException;

  /**
   * Build a User Operation to execute a custom call data from a sender
   *
   * @param sender   the sender address
   * @param callData the call data
   */
  UserOperation buildCustomUserOperation(String sender, byte[] callData) throws IllegalArgumentException, IOException, VoltaException;

  /**
   * Suggest gas tip cap on the blockchain
   */
  BigInteger suggestGasTipCap() throws IOException, VoltaException;

  /**
   * Get base fee per gas on the blockchain
   */
  BigInteger getBaseFeePerGas() throws IOException, VoltaException;
}
