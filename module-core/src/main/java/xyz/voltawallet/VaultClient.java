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

  /**
   * Get next nonce of a Vault address
   *
   * @param senderAddress the vault address
   */
  BigInteger nextNonce(String senderAddress);


  /**
   * Build a User Operation to execute a {@link Call} on a {@link Vault}
   *
   * @param vault the {@link Vault} information
   * @param call  the {@link Call} request
   */
  UserOperation buildExecuteUserOperation(Vault vault, Call call) throws IOException, VoltaException;

  /**
   * Build a User Operation to execute a {@link Transaction} on a {@link Vault}
   *
   * @param vault the {@link Vault} information
   * @param tx    the {@link Transaction} request
   */
  UserOperation buildExecuteUserOperationFromTx(Vault vault, Transaction tx) throws IOException, VoltaException;

  /**
   * Build a User Operation to execute a batch of {@link Call} on a {@link Vault}
   *
   * @param vault the {@link Vault} information
   * @param calls the list of {@link Call} request
   */
  UserOperation buildExecuteBatchUserOperation(Vault vault, List<Call> calls) throws IllegalArgumentException, IOException, VoltaException;

  /**
   * Build a User Operation to execute a custom call data on a {@link Vault}
   *
   * @param vault    the {@link Vault} information
   * @param callData the call data
   */
  UserOperation buildCustomUserOperation(Vault vault, byte[] callData) throws IllegalArgumentException, IOException, VoltaException;

  /**
   * Suggest gas price for a {@link UserOperation}
   *
   * @param userOperation the User Operation for estimate gas price
   */
  UserOperation suggestUserOpGasPrice(UserOperation userOperation) throws IOException, VoltaException;
}
