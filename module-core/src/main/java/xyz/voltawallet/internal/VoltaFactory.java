package xyz.voltawallet.internal;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticEIP1559GasProvider;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.VoltaClient;
import xyz.voltawallet.contracts.EntryPoint;
import xyz.voltawallet.contracts.VoltaAccount;
import xyz.voltawallet.model.ContractAddresses;

import java.math.BigInteger;

public final class VoltaFactory {

  private static final ObjectMapper JACKSON = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private static final OkHttpClient OKHTTP = new OkHttpClient();

  private static volatile VoltaClient instance = null;

  /**
   * Provide a singleton instance of {@link VoltaClient}
   *
   * @param bundleServiceUrl The url of the bundle service
   * @return A singleton instance of {@link VoltaClient}
   */
  public static VoltaClient createVoltaClient(String bundleServiceUrl) {
    if (instance == null) {
      synchronized (VoltaFactory.class) {
        if (instance == null) {
          instance = new DefaultVoltaClient(
            new DefaultBundleClient(
              JACKSON,
              OKHTTP,
              bundleServiceUrl
            )
          );
        }
      }
    }
    return instance;
  }

  public static VaultClient createVaultClient(long chainId, String bundleServiceUrl, ContractAddresses contractAddresses) {
    Web3j web3j = Web3j.build(new HttpService(bundleServiceUrl));
    TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddresses.getEntryPoint());
    ContractGasProvider contractGasProvider = new StaticEIP1559GasProvider(
      chainId,
      new BigInteger("39000000000"),
      new BigInteger("218000000000"),
      new BigInteger("21000")
    );

    return new DefaultVaultClient(
      EntryPoint.load(contractAddresses.getEntryPoint(), web3j, transactionManager, contractGasProvider),
      xyz.voltawallet.contracts.VoltaFactory.load(contractAddresses.getFactory(), web3j, transactionManager, contractGasProvider),
      VoltaAccount.load(contractAddresses.getFactory(), web3j, transactionManager, contractGasProvider),
      new DefaultBundleClient(
        JACKSON,
        OKHTTP,
        bundleServiceUrl
      ),
      contractAddresses
    );
  }
}
