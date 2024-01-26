package xyz.voltawallet.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class VoltaAccount extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_EXECUTEBATCH = "executeBatch";

    @Deprecated
    protected VoltaAccount(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VoltaAccount(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VoltaAccount(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VoltaAccount(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(String dest, BigInteger value, byte[] data) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, dest), 
                new org.web3j.abi.datatypes.generated.Uint256(value), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> executeBatch(List<Call> calls) {
        final Function function = new Function(
                FUNC_EXECUTEBATCH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<Call>(Call.class, calls)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static VoltaAccount load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VoltaAccount(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VoltaAccount load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VoltaAccount(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VoltaAccount load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VoltaAccount(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VoltaAccount load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VoltaAccount(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class Call extends DynamicStruct {
        public String target;

        public BigInteger value;

        public byte[] data;

        public Call(String target, BigInteger value, byte[] data) {
            super(new org.web3j.abi.datatypes.Address(160, target), 
                    new org.web3j.abi.datatypes.generated.Uint256(value), 
                    new org.web3j.abi.datatypes.DynamicBytes(data));
            this.target = target;
            this.value = value;
            this.data = data;
        }

        public Call(Address target, Uint256 value, DynamicBytes data) {
            super(target, value, data);
            this.target = target.getValue();
            this.value = value.getValue();
            this.data = data.getValue();
        }
    }
}
