package xyz.voltawallet.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class VoltaFactory extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_ADDSTAKE = "addStake";

    public static final String FUNC_CANCELOWNERSHIPHANDOVER = "cancelOwnershipHandover";

    public static final String FUNC_COMPLETEOWNERSHIPHANDOVER = "completeOwnershipHandover";

    public static final String FUNC_CREATEACCOUNT = "createAccount";

    public static final String FUNC_ENTRYPOINT = "entryPoint";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNERSHIPHANDOVEREXPIRESAT = "ownershipHandoverExpiresAt";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REQUESTOWNERSHIPHANDOVER = "requestOwnershipHandover";

    public static final String FUNC_SETENTRYPOINT = "setEntryPoint";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNLOCKSTAKE = "unlockStake";

    public static final String FUNC_WITHDRAWSTAKE = "withdrawStake";

    public static final Event OWNERSHIPHANDOVERCANCELED_EVENT = new Event("OwnershipHandoverCanceled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPHANDOVERREQUESTED_EVENT = new Event("OwnershipHandoverRequested", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected VoltaFactory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VoltaFactory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VoltaFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VoltaFactory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<OwnershipHandoverCanceledEventResponse> getOwnershipHandoverCanceledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPHANDOVERCANCELED_EVENT, transactionReceipt);
        ArrayList<OwnershipHandoverCanceledEventResponse> responses = new ArrayList<OwnershipHandoverCanceledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipHandoverCanceledEventResponse typedResponse = new OwnershipHandoverCanceledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.pendingOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipHandoverCanceledEventResponse getOwnershipHandoverCanceledEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPHANDOVERCANCELED_EVENT, log);
        OwnershipHandoverCanceledEventResponse typedResponse = new OwnershipHandoverCanceledEventResponse();
        typedResponse.log = log;
        typedResponse.pendingOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipHandoverCanceledEventResponse> ownershipHandoverCanceledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipHandoverCanceledEventFromLog(log));
    }

    public Flowable<OwnershipHandoverCanceledEventResponse> ownershipHandoverCanceledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPHANDOVERCANCELED_EVENT));
        return ownershipHandoverCanceledEventFlowable(filter);
    }

    public static List<OwnershipHandoverRequestedEventResponse> getOwnershipHandoverRequestedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPHANDOVERREQUESTED_EVENT, transactionReceipt);
        ArrayList<OwnershipHandoverRequestedEventResponse> responses = new ArrayList<OwnershipHandoverRequestedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipHandoverRequestedEventResponse typedResponse = new OwnershipHandoverRequestedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.pendingOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipHandoverRequestedEventResponse getOwnershipHandoverRequestedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPHANDOVERREQUESTED_EVENT, log);
        OwnershipHandoverRequestedEventResponse typedResponse = new OwnershipHandoverRequestedEventResponse();
        typedResponse.log = log;
        typedResponse.pendingOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipHandoverRequestedEventResponse> ownershipHandoverRequestedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipHandoverRequestedEventFromLog(log));
    }

    public Flowable<OwnershipHandoverRequestedEventResponse> ownershipHandoverRequestedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPHANDOVERREQUESTED_EVENT));
        return ownershipHandoverRequestedEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addStake(BigInteger unstakeDelaySec, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(unstakeDelaySec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelOwnershipHandover(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CANCELOWNERSHIPHANDOVER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> completeOwnershipHandover(String pendingOwner, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_COMPLETEOWNERSHIPHANDOVER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pendingOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> createAccount(String accountImplementation, String sessionKeyValidatorImplementation, String executorImplementation, List<String> owners, BigInteger minNumSigners, BigInteger salt) {
        final Function function = new Function(
                FUNC_CREATEACCOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, accountImplementation), 
                new org.web3j.abi.datatypes.Address(160, sessionKeyValidatorImplementation), 
                new org.web3j.abi.datatypes.Address(160, executorImplementation), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(owners, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(minNumSigners), 
                new org.web3j.abi.datatypes.generated.Uint256(salt)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> entryPoint() {
        final Function function = new Function(FUNC_ENTRYPOINT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getAddress(String accountImplementation, String sessionKeyValidatorImplementation, String executorImplementation, List<String> owners, BigInteger minNumSigners, BigInteger salt) {
        final Function function = new Function(FUNC_GETADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, accountImplementation), 
                new org.web3j.abi.datatypes.Address(160, sessionKeyValidatorImplementation), 
                new org.web3j.abi.datatypes.Address(160, executorImplementation), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(owners, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(minNumSigners), 
                new org.web3j.abi.datatypes.generated.Uint256(salt)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> ownershipHandoverExpiresAt(String pendingOwner) {
        final Function function = new Function(FUNC_OWNERSHIPHANDOVEREXPIRESAT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, pendingOwner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> requestOwnershipHandover(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_REQUESTOWNERSHIPHANDOVER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> setEntryPoint(String _entryPoint) {
        final Function function = new Function(
                FUNC_SETENTRYPOINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _entryPoint)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> unlockStake() {
        final Function function = new Function(
                FUNC_UNLOCKSTAKE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawStake(String withdrawAddress) {
        final Function function = new Function(
                FUNC_WITHDRAWSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, withdrawAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static VoltaFactory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VoltaFactory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VoltaFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VoltaFactory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VoltaFactory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VoltaFactory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VoltaFactory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VoltaFactory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class OwnershipHandoverCanceledEventResponse extends BaseEventResponse {
        public String pendingOwner;
    }

    public static class OwnershipHandoverRequestedEventResponse extends BaseEventResponse {
        public String pendingOwner;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String oldOwner;

        public String newOwner;
    }
}
