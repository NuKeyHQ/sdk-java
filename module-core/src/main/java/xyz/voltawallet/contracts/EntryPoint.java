package xyz.voltawallet.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint112;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint48;
import org.web3j.abi.datatypes.reflection.Parameterized;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
public class EntryPoint extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_SIG_VALIDATION_FAILED = "SIG_VALIDATION_FAILED";

    public static final String FUNC__VALIDATESENDERANDPAYMASTER = "_validateSenderAndPaymaster";

    public static final String FUNC_ADDSTAKE = "addStake";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DEPOSITTO = "depositTo";

    public static final String FUNC_DEPOSITS = "deposits";

    public static final String FUNC_GETDEPOSITINFO = "getDepositInfo";

    public static final String FUNC_GETNONCE = "getNonce";

    public static final String FUNC_GETSENDERADDRESS = "getSenderAddress";

    public static final String FUNC_GETUSEROPHASH = "getUserOpHash";

    public static final String FUNC_HANDLEAGGREGATEDOPS = "handleAggregatedOps";

    public static final String FUNC_HANDLEOPS = "handleOps";

    public static final String FUNC_INCREMENTNONCE = "incrementNonce";

    public static final String FUNC_INNERHANDLEOP = "innerHandleOp";

    public static final String FUNC_NONCESEQUENCENUMBER = "nonceSequenceNumber";

    public static final String FUNC_SIMULATEHANDLEOP = "simulateHandleOp";

    public static final String FUNC_SIMULATEVALIDATION = "simulateValidation";

    public static final String FUNC_UNLOCKSTAKE = "unlockStake";

    public static final String FUNC_WITHDRAWSTAKE = "withdrawStake";

    public static final String FUNC_WITHDRAWTO = "withdrawTo";

    public static final Event ACCOUNTDEPLOYED_EVENT = new Event("AccountDeployed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event BEFOREEXECUTION_EVENT = new Event("BeforeExecution", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SIGNATUREAGGREGATORCHANGED_EVENT = new Event("SignatureAggregatorChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event STAKELOCKED_EVENT = new Event("StakeLocked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STAKEUNLOCKED_EVENT = new Event("StakeUnlocked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STAKEWITHDRAWN_EVENT = new Event("StakeWithdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event USEROPERATIONEVENT_EVENT = new Event("UserOperationEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event USEROPERATIONREVERTREASON_EVENT = new Event("UserOperationRevertReason", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event WITHDRAWN_EVENT = new Event("Withdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected EntryPoint(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EntryPoint(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EntryPoint(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EntryPoint(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AccountDeployedEventResponse> getAccountDeployedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ACCOUNTDEPLOYED_EVENT, transactionReceipt);
        ArrayList<AccountDeployedEventResponse> responses = new ArrayList<AccountDeployedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AccountDeployedEventResponse typedResponse = new AccountDeployedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.factory = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.paymaster = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AccountDeployedEventResponse getAccountDeployedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ACCOUNTDEPLOYED_EVENT, log);
        AccountDeployedEventResponse typedResponse = new AccountDeployedEventResponse();
        typedResponse.log = log;
        typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.factory = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.paymaster = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<AccountDeployedEventResponse> accountDeployedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAccountDeployedEventFromLog(log));
    }

    public Flowable<AccountDeployedEventResponse> accountDeployedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ACCOUNTDEPLOYED_EVENT));
        return accountDeployedEventFlowable(filter);
    }

    public static List<BeforeExecutionEventResponse> getBeforeExecutionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BEFOREEXECUTION_EVENT, transactionReceipt);
        ArrayList<BeforeExecutionEventResponse> responses = new ArrayList<BeforeExecutionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BeforeExecutionEventResponse typedResponse = new BeforeExecutionEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static BeforeExecutionEventResponse getBeforeExecutionEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(BEFOREEXECUTION_EVENT, log);
        BeforeExecutionEventResponse typedResponse = new BeforeExecutionEventResponse();
        typedResponse.log = log;
        return typedResponse;
    }

    public Flowable<BeforeExecutionEventResponse> beforeExecutionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getBeforeExecutionEventFromLog(log));
    }

    public Flowable<BeforeExecutionEventResponse> beforeExecutionEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BEFOREEXECUTION_EVENT));
        return beforeExecutionEventFlowable(filter);
    }

    public static List<DepositedEventResponse> getDepositedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(DEPOSITED_EVENT, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.totalDeposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DepositedEventResponse getDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DEPOSITED_EVENT, log);
        DepositedEventResponse typedResponse = new DepositedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.totalDeposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDepositedEventFromLog(log));
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSITED_EVENT));
        return depositedEventFlowable(filter);
    }

    public static List<SignatureAggregatorChangedEventResponse> getSignatureAggregatorChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SIGNATUREAGGREGATORCHANGED_EVENT, transactionReceipt);
        ArrayList<SignatureAggregatorChangedEventResponse> responses = new ArrayList<SignatureAggregatorChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SignatureAggregatorChangedEventResponse typedResponse = new SignatureAggregatorChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.aggregator = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SignatureAggregatorChangedEventResponse getSignatureAggregatorChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SIGNATUREAGGREGATORCHANGED_EVENT, log);
        SignatureAggregatorChangedEventResponse typedResponse = new SignatureAggregatorChangedEventResponse();
        typedResponse.log = log;
        typedResponse.aggregator = (String) eventValues.getIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<SignatureAggregatorChangedEventResponse> signatureAggregatorChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSignatureAggregatorChangedEventFromLog(log));
    }

    public Flowable<SignatureAggregatorChangedEventResponse> signatureAggregatorChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SIGNATUREAGGREGATORCHANGED_EVENT));
        return signatureAggregatorChangedEventFlowable(filter);
    }

    public static List<StakeLockedEventResponse> getStakeLockedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKELOCKED_EVENT, transactionReceipt);
        ArrayList<StakeLockedEventResponse> responses = new ArrayList<StakeLockedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StakeLockedEventResponse typedResponse = new StakeLockedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.totalStaked = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.unstakeDelaySec = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakeLockedEventResponse getStakeLockedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKELOCKED_EVENT, log);
        StakeLockedEventResponse typedResponse = new StakeLockedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.totalStaked = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.unstakeDelaySec = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<StakeLockedEventResponse> stakeLockedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakeLockedEventFromLog(log));
    }

    public Flowable<StakeLockedEventResponse> stakeLockedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKELOCKED_EVENT));
        return stakeLockedEventFlowable(filter);
    }

    public static List<StakeUnlockedEventResponse> getStakeUnlockedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKEUNLOCKED_EVENT, transactionReceipt);
        ArrayList<StakeUnlockedEventResponse> responses = new ArrayList<StakeUnlockedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StakeUnlockedEventResponse typedResponse = new StakeUnlockedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.withdrawTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakeUnlockedEventResponse getStakeUnlockedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKEUNLOCKED_EVENT, log);
        StakeUnlockedEventResponse typedResponse = new StakeUnlockedEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.withdrawTime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<StakeUnlockedEventResponse> stakeUnlockedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakeUnlockedEventFromLog(log));
    }

    public Flowable<StakeUnlockedEventResponse> stakeUnlockedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKEUNLOCKED_EVENT));
        return stakeUnlockedEventFlowable(filter);
    }

    public static List<StakeWithdrawnEventResponse> getStakeWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(STAKEWITHDRAWN_EVENT, transactionReceipt);
        ArrayList<StakeWithdrawnEventResponse> responses = new ArrayList<StakeWithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StakeWithdrawnEventResponse typedResponse = new StakeWithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.withdrawAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static StakeWithdrawnEventResponse getStakeWithdrawnEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(STAKEWITHDRAWN_EVENT, log);
        StakeWithdrawnEventResponse typedResponse = new StakeWithdrawnEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.withdrawAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<StakeWithdrawnEventResponse> stakeWithdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getStakeWithdrawnEventFromLog(log));
    }

    public Flowable<StakeWithdrawnEventResponse> stakeWithdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STAKEWITHDRAWN_EVENT));
        return stakeWithdrawnEventFlowable(filter);
    }

    public static List<UserOperationEventEventResponse> getUserOperationEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(USEROPERATIONEVENT_EVENT, transactionReceipt);
        ArrayList<UserOperationEventEventResponse> responses = new ArrayList<UserOperationEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UserOperationEventEventResponse typedResponse = new UserOperationEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.paymaster = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.actualGasCost = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.actualGasUsed = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UserOperationEventEventResponse getUserOperationEventEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(USEROPERATIONEVENT_EVENT, log);
        UserOperationEventEventResponse typedResponse = new UserOperationEventEventResponse();
        typedResponse.log = log;
        typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.paymaster = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.success = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.actualGasCost = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.actualGasUsed = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<UserOperationEventEventResponse> userOperationEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUserOperationEventEventFromLog(log));
    }

    public Flowable<UserOperationEventEventResponse> userOperationEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USEROPERATIONEVENT_EVENT));
        return userOperationEventEventFlowable(filter);
    }

    public static List<UserOperationRevertReasonEventResponse> getUserOperationRevertReasonEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(USEROPERATIONREVERTREASON_EVENT, transactionReceipt);
        ArrayList<UserOperationRevertReasonEventResponse> responses = new ArrayList<UserOperationRevertReasonEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UserOperationRevertReasonEventResponse typedResponse = new UserOperationRevertReasonEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.revertReason = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UserOperationRevertReasonEventResponse getUserOperationRevertReasonEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(USEROPERATIONREVERTREASON_EVENT, log);
        UserOperationRevertReasonEventResponse typedResponse = new UserOperationRevertReasonEventResponse();
        typedResponse.log = log;
        typedResponse.userOpHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.nonce = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.revertReason = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<UserOperationRevertReasonEventResponse> userOperationRevertReasonEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUserOperationRevertReasonEventFromLog(log));
    }

    public Flowable<UserOperationRevertReasonEventResponse> userOperationRevertReasonEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USEROPERATIONREVERTREASON_EVENT));
        return userOperationRevertReasonEventFlowable(filter);
    }

    public static List<WithdrawnEventResponse> getWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(WITHDRAWN_EVENT, transactionReceipt);
        ArrayList<WithdrawnEventResponse> responses = new ArrayList<WithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.withdrawAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static WithdrawnEventResponse getWithdrawnEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(WITHDRAWN_EVENT, log);
        WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
        typedResponse.log = log;
        typedResponse.account = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.withdrawAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getWithdrawnEventFromLog(log));
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWN_EVENT));
        return withdrawnEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> SIG_VALIDATION_FAILED() {
        final Function function = new Function(FUNC_SIG_VALIDATION_FAILED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addStake(BigInteger unstakeDelaySec, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_ADDSTAKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(unstakeDelaySec)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> depositTo(String account, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSITTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, BigInteger>> deposits(String param0) {
        final Function function = new Function(FUNC_DEPOSITS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint112>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint112>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint48>() {}));
        return new RemoteFunctionCall<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, Boolean, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, Boolean, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<DepositInfo> getDepositInfo(String account) {
        final Function function = new Function(FUNC_GETDEPOSITINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DepositInfo>() {}));
        return executeRemoteCallSingleValueReturn(function, DepositInfo.class);
    }

    public RemoteFunctionCall<BigInteger> getNonce(String sender, BigInteger key) {
        final Function function = new Function(FUNC_GETNONCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.generated.Uint192(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getSenderAddress(byte[] initCode) {
        final Function function = new Function(
                FUNC_GETSENDERADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(initCode)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<byte[]> getUserOpHash(UserOperation userOp) {
        final Function function = new Function(FUNC_GETUSEROPHASH, 
                Arrays.<Type>asList(userOp), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> handleAggregatedOps(List<UserOpsPerAggregator> opsPerAggregator, String beneficiary) {
        final Function function = new Function(
                FUNC_HANDLEAGGREGATEDOPS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<UserOpsPerAggregator>(UserOpsPerAggregator.class, opsPerAggregator), 
                new org.web3j.abi.datatypes.Address(160, beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> handleOps(List<UserOperation> ops, String beneficiary) {
        final Function function = new Function(
                FUNC_HANDLEOPS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<UserOperation>(UserOperation.class, ops), 
                new org.web3j.abi.datatypes.Address(160, beneficiary)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> incrementNonce(BigInteger key) {
        final Function function = new Function(
                FUNC_INCREMENTNONCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint192(key)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> innerHandleOp(byte[] callData, UserOpInfo opInfo, byte[] context) {
        final Function function = new Function(
                FUNC_INNERHANDLEOP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicBytes(callData), 
                opInfo, 
                new org.web3j.abi.datatypes.DynamicBytes(context)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> nonceSequenceNumber(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_NONCESEQUENCENUMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0), 
                new org.web3j.abi.datatypes.generated.Uint192(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> simulateHandleOp(UserOperation op, String target, byte[] targetCallData) {
        final Function function = new Function(
                FUNC_SIMULATEHANDLEOP, 
                Arrays.<Type>asList(op, 
                new org.web3j.abi.datatypes.Address(160, target), 
                new org.web3j.abi.datatypes.DynamicBytes(targetCallData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> simulateValidation(UserOperation userOp) {
        final Function function = new Function(
                FUNC_SIMULATEVALIDATION, 
                Arrays.<Type>asList(userOp), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> withdrawTo(String withdrawAddress, BigInteger withdrawAmount) {
        final Function function = new Function(
                FUNC_WITHDRAWTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, withdrawAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(withdrawAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EntryPoint load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EntryPoint(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EntryPoint load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EntryPoint(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EntryPoint load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EntryPoint(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EntryPoint load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EntryPoint(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class ReturnInfo extends DynamicStruct {
        public BigInteger preOpGas;

        public BigInteger prefund;

        public Boolean sigFailed;

        public BigInteger validAfter;

        public BigInteger validUntil;

        public byte[] paymasterContext;

        public ReturnInfo(BigInteger preOpGas, BigInteger prefund, Boolean sigFailed, BigInteger validAfter, BigInteger validUntil, byte[] paymasterContext) {
            super(new org.web3j.abi.datatypes.generated.Uint256(preOpGas), 
                    new org.web3j.abi.datatypes.generated.Uint256(prefund), 
                    new org.web3j.abi.datatypes.Bool(sigFailed), 
                    new org.web3j.abi.datatypes.generated.Uint48(validAfter), 
                    new org.web3j.abi.datatypes.generated.Uint48(validUntil), 
                    new org.web3j.abi.datatypes.DynamicBytes(paymasterContext));
            this.preOpGas = preOpGas;
            this.prefund = prefund;
            this.sigFailed = sigFailed;
            this.validAfter = validAfter;
            this.validUntil = validUntil;
            this.paymasterContext = paymasterContext;
        }

        public ReturnInfo(Uint256 preOpGas, Uint256 prefund, Bool sigFailed, Uint48 validAfter, Uint48 validUntil, DynamicBytes paymasterContext) {
            super(preOpGas, prefund, sigFailed, validAfter, validUntil, paymasterContext);
            this.preOpGas = preOpGas.getValue();
            this.prefund = prefund.getValue();
            this.sigFailed = sigFailed.getValue();
            this.validAfter = validAfter.getValue();
            this.validUntil = validUntil.getValue();
            this.paymasterContext = paymasterContext.getValue();
        }
    }

    public static class StakeInfo extends StaticStruct {
        public BigInteger stake;

        public BigInteger unstakeDelaySec;

        public StakeInfo(BigInteger stake, BigInteger unstakeDelaySec) {
            super(new org.web3j.abi.datatypes.generated.Uint256(stake), 
                    new org.web3j.abi.datatypes.generated.Uint256(unstakeDelaySec));
            this.stake = stake;
            this.unstakeDelaySec = unstakeDelaySec;
        }

        public StakeInfo(Uint256 stake, Uint256 unstakeDelaySec) {
            super(stake, unstakeDelaySec);
            this.stake = stake.getValue();
            this.unstakeDelaySec = unstakeDelaySec.getValue();
        }
    }

    public static class DepositInfo extends StaticStruct {
        public BigInteger deposit;

        public Boolean staked;

        public BigInteger stake;

        public BigInteger unstakeDelaySec;

        public BigInteger withdrawTime;

        public DepositInfo(BigInteger deposit, Boolean staked, BigInteger stake, BigInteger unstakeDelaySec, BigInteger withdrawTime) {
            super(new org.web3j.abi.datatypes.generated.Uint112(deposit), 
                    new org.web3j.abi.datatypes.Bool(staked), 
                    new org.web3j.abi.datatypes.generated.Uint112(stake), 
                    new org.web3j.abi.datatypes.generated.Uint32(unstakeDelaySec), 
                    new org.web3j.abi.datatypes.generated.Uint48(withdrawTime));
            this.deposit = deposit;
            this.staked = staked;
            this.stake = stake;
            this.unstakeDelaySec = unstakeDelaySec;
            this.withdrawTime = withdrawTime;
        }

        public DepositInfo(Uint112 deposit, Bool staked, Uint112 stake, Uint32 unstakeDelaySec, Uint48 withdrawTime) {
            super(deposit, staked, stake, unstakeDelaySec, withdrawTime);
            this.deposit = deposit.getValue();
            this.staked = staked.getValue();
            this.stake = stake.getValue();
            this.unstakeDelaySec = unstakeDelaySec.getValue();
            this.withdrawTime = withdrawTime.getValue();
        }
    }

    public static class UserOperation extends DynamicStruct {
        public String sender;

        public BigInteger nonce;

        public byte[] initCode;

        public byte[] callData;

        public BigInteger callGasLimit;

        public BigInteger verificationGasLimit;

        public BigInteger preVerificationGas;

        public BigInteger maxFeePerGas;

        public BigInteger maxPriorityFeePerGas;

        public byte[] paymasterAndData;

        public byte[] signature;

        public UserOperation(String sender, BigInteger nonce, byte[] initCode, byte[] callData, BigInteger callGasLimit, BigInteger verificationGasLimit, BigInteger preVerificationGas, BigInteger maxFeePerGas, BigInteger maxPriorityFeePerGas, byte[] paymasterAndData, byte[] signature) {
            super(new org.web3j.abi.datatypes.Address(160, sender), 
                    new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                    new org.web3j.abi.datatypes.DynamicBytes(initCode), 
                    new org.web3j.abi.datatypes.DynamicBytes(callData), 
                    new org.web3j.abi.datatypes.generated.Uint256(callGasLimit), 
                    new org.web3j.abi.datatypes.generated.Uint256(verificationGasLimit), 
                    new org.web3j.abi.datatypes.generated.Uint256(preVerificationGas), 
                    new org.web3j.abi.datatypes.generated.Uint256(maxFeePerGas), 
                    new org.web3j.abi.datatypes.generated.Uint256(maxPriorityFeePerGas), 
                    new org.web3j.abi.datatypes.DynamicBytes(paymasterAndData), 
                    new org.web3j.abi.datatypes.DynamicBytes(signature));
            this.sender = sender;
            this.nonce = nonce;
            this.initCode = initCode;
            this.callData = callData;
            this.callGasLimit = callGasLimit;
            this.verificationGasLimit = verificationGasLimit;
            this.preVerificationGas = preVerificationGas;
            this.maxFeePerGas = maxFeePerGas;
            this.maxPriorityFeePerGas = maxPriorityFeePerGas;
            this.paymasterAndData = paymasterAndData;
            this.signature = signature;
        }

        public UserOperation(Address sender, Uint256 nonce, DynamicBytes initCode, DynamicBytes callData, Uint256 callGasLimit, Uint256 verificationGasLimit, Uint256 preVerificationGas, Uint256 maxFeePerGas, Uint256 maxPriorityFeePerGas, DynamicBytes paymasterAndData, DynamicBytes signature) {
            super(sender, nonce, initCode, callData, callGasLimit, verificationGasLimit, preVerificationGas, maxFeePerGas, maxPriorityFeePerGas, paymasterAndData, signature);
            this.sender = sender.getValue();
            this.nonce = nonce.getValue();
            this.initCode = initCode.getValue();
            this.callData = callData.getValue();
            this.callGasLimit = callGasLimit.getValue();
            this.verificationGasLimit = verificationGasLimit.getValue();
            this.preVerificationGas = preVerificationGas.getValue();
            this.maxFeePerGas = maxFeePerGas.getValue();
            this.maxPriorityFeePerGas = maxPriorityFeePerGas.getValue();
            this.paymasterAndData = paymasterAndData.getValue();
            this.signature = signature.getValue();
        }
    }

    public static class MemoryUserOp extends StaticStruct {
        public String sender;

        public BigInteger nonce;

        public BigInteger callGasLimit;

        public BigInteger verificationGasLimit;

        public BigInteger preVerificationGas;

        public String paymaster;

        public BigInteger maxFeePerGas;

        public BigInteger maxPriorityFeePerGas;

        public MemoryUserOp(String sender, BigInteger nonce, BigInteger callGasLimit, BigInteger verificationGasLimit, BigInteger preVerificationGas, String paymaster, BigInteger maxFeePerGas, BigInteger maxPriorityFeePerGas) {
            super(new org.web3j.abi.datatypes.Address(160, sender), 
                    new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                    new org.web3j.abi.datatypes.generated.Uint256(callGasLimit), 
                    new org.web3j.abi.datatypes.generated.Uint256(verificationGasLimit), 
                    new org.web3j.abi.datatypes.generated.Uint256(preVerificationGas), 
                    new org.web3j.abi.datatypes.Address(160, paymaster), 
                    new org.web3j.abi.datatypes.generated.Uint256(maxFeePerGas), 
                    new org.web3j.abi.datatypes.generated.Uint256(maxPriorityFeePerGas));
            this.sender = sender;
            this.nonce = nonce;
            this.callGasLimit = callGasLimit;
            this.verificationGasLimit = verificationGasLimit;
            this.preVerificationGas = preVerificationGas;
            this.paymaster = paymaster;
            this.maxFeePerGas = maxFeePerGas;
            this.maxPriorityFeePerGas = maxPriorityFeePerGas;
        }

        public MemoryUserOp(Address sender, Uint256 nonce, Uint256 callGasLimit, Uint256 verificationGasLimit, Uint256 preVerificationGas, Address paymaster, Uint256 maxFeePerGas, Uint256 maxPriorityFeePerGas) {
            super(sender, nonce, callGasLimit, verificationGasLimit, preVerificationGas, paymaster, maxFeePerGas, maxPriorityFeePerGas);
            this.sender = sender.getValue();
            this.nonce = nonce.getValue();
            this.callGasLimit = callGasLimit.getValue();
            this.verificationGasLimit = verificationGasLimit.getValue();
            this.preVerificationGas = preVerificationGas.getValue();
            this.paymaster = paymaster.getValue();
            this.maxFeePerGas = maxFeePerGas.getValue();
            this.maxPriorityFeePerGas = maxPriorityFeePerGas.getValue();
        }
    }

    public static class AggregatorStakeInfo extends StaticStruct {
        public String aggregator;

        public StakeInfo stakeInfo;

        public AggregatorStakeInfo(String aggregator, StakeInfo stakeInfo) {
            super(new org.web3j.abi.datatypes.Address(160, aggregator), 
                    stakeInfo);
            this.aggregator = aggregator;
            this.stakeInfo = stakeInfo;
        }

        public AggregatorStakeInfo(Address aggregator, StakeInfo stakeInfo) {
            super(aggregator, stakeInfo);
            this.aggregator = aggregator.getValue();
            this.stakeInfo = stakeInfo;
        }
    }

    public static class UserOpsPerAggregator extends DynamicStruct {
        public List<UserOperation> userOps;

        public String aggregator;

        public byte[] signature;

        public UserOpsPerAggregator(List<UserOperation> userOps, String aggregator, byte[] signature) {
            super(new org.web3j.abi.datatypes.DynamicArray<UserOperation>(UserOperation.class, userOps), 
                    new org.web3j.abi.datatypes.Address(160, aggregator), 
                    new org.web3j.abi.datatypes.DynamicBytes(signature));
            this.userOps = userOps;
            this.aggregator = aggregator;
            this.signature = signature;
        }

        public UserOpsPerAggregator(@Parameterized(type = UserOperation.class) DynamicArray<UserOperation> userOps, Address aggregator, DynamicBytes signature) {
            super(userOps, aggregator, signature);
            this.userOps = userOps.getValue();
            this.aggregator = aggregator.getValue();
            this.signature = signature.getValue();
        }
    }

    public static class UserOpInfo extends StaticStruct {
        public MemoryUserOp mUserOp;

        public byte[] userOpHash;

        public BigInteger prefund;

        public BigInteger contextOffset;

        public BigInteger preOpGas;

        public UserOpInfo(MemoryUserOp mUserOp, byte[] userOpHash, BigInteger prefund, BigInteger contextOffset, BigInteger preOpGas) {
            super(mUserOp, 
                    new org.web3j.abi.datatypes.generated.Bytes32(userOpHash), 
                    new org.web3j.abi.datatypes.generated.Uint256(prefund), 
                    new org.web3j.abi.datatypes.generated.Uint256(contextOffset), 
                    new org.web3j.abi.datatypes.generated.Uint256(preOpGas));
            this.mUserOp = mUserOp;
            this.userOpHash = userOpHash;
            this.prefund = prefund;
            this.contextOffset = contextOffset;
            this.preOpGas = preOpGas;
        }

        public UserOpInfo(MemoryUserOp mUserOp, Bytes32 userOpHash, Uint256 prefund, Uint256 contextOffset, Uint256 preOpGas) {
            super(mUserOp, userOpHash, prefund, contextOffset, preOpGas);
            this.mUserOp = mUserOp;
            this.userOpHash = userOpHash.getValue();
            this.prefund = prefund.getValue();
            this.contextOffset = contextOffset.getValue();
            this.preOpGas = preOpGas.getValue();
        }
    }

    public static class AccountDeployedEventResponse extends BaseEventResponse {
        public byte[] userOpHash;

        public String sender;

        public String factory;

        public String paymaster;
    }

    public static class BeforeExecutionEventResponse extends BaseEventResponse {
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public String account;

        public BigInteger totalDeposit;
    }

    public static class SignatureAggregatorChangedEventResponse extends BaseEventResponse {
        public String aggregator;
    }

    public static class StakeLockedEventResponse extends BaseEventResponse {
        public String account;

        public BigInteger totalStaked;

        public BigInteger unstakeDelaySec;
    }

    public static class StakeUnlockedEventResponse extends BaseEventResponse {
        public String account;

        public BigInteger withdrawTime;
    }

    public static class StakeWithdrawnEventResponse extends BaseEventResponse {
        public String account;

        public String withdrawAddress;

        public BigInteger amount;
    }

    public static class UserOperationEventEventResponse extends BaseEventResponse {
        public byte[] userOpHash;

        public String sender;

        public String paymaster;

        public BigInteger nonce;

        public Boolean success;

        public BigInteger actualGasCost;

        public BigInteger actualGasUsed;
    }

    public static class UserOperationRevertReasonEventResponse extends BaseEventResponse {
        public byte[] userOpHash;

        public String sender;

        public BigInteger nonce;

        public byte[] revertReason;
    }

    public static class WithdrawnEventResponse extends BaseEventResponse {
        public String account;

        public String withdrawAddress;

        public BigInteger amount;
    }
}
