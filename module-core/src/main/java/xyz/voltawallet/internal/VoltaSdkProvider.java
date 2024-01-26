package xyz.voltawallet.internal;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import xyz.voltawallet.BundlerClient;
import xyz.voltawallet.VaultClient;
import xyz.voltawallet.contracts.EntryPoint;
import xyz.voltawallet.contracts.VoltaAccount;
import xyz.voltawallet.model.ContractAddressesConfig;

public final class VoltaSdkProvider {

  private static final ObjectMapper JACKSON = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private static final OkHttpClient OKHTTP = new OkHttpClient();

  public static BundlerClient createBundlerClient(String bundlerServiceUrl) {
    return new DefaultBundlerClient(
      JACKSON,
      OKHTTP,
      bundlerServiceUrl
    );
  }

  public static VaultClient createVaultClient(String bundleServiceUrl, ContractAddressesConfig contractAddressesConfig) {
    Web3j web3j = Web3j.build(new HttpService(bundleServiceUrl));
    TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, contractAddressesConfig.getEntryPoint());
    ContractGasProvider contractGasProvider = new DefaultGasProvider();

    return new DefaultVaultClient(
      EntryPoint.load(contractAddressesConfig.getEntryPoint(), web3j, transactionManager, contractGasProvider),
      VoltaAccount.load(contractAddressesConfig.getFactory(), web3j, transactionManager, contractGasProvider),
      contractAddressesConfig,
      web3j
    );
  }
}
