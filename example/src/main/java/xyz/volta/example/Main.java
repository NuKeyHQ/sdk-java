package xyz.volta.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import xyz.volta.VoltaSdk;
import xyz.volta.VoltaSessionKeyClient;
import xyz.volta.constant.Blockchain;
import xyz.volta.model.UserOperation;

import java.util.Arrays;

/**
 * An example about using Volta sdk-java for sign and send a user operation.<br>
 * <b>How to run this example</b><br>
 * 1. Using the tools are mentioned in
 * <a href="https://nukey.atlassian.net/wiki/spaces/NUKEY/pages/97779713/Testing+ERC4337+Wallet+Actionsthis">this wiki page</a>
 * to create User Operation data, then replace the data in {@link Main.USR_OP_STR} variable.<br>
 * 2. Also replace {@link Main.PRIVATE_KEY_1} and {@link Main.PRIVATE_KEY_2} by your private keys.<br>
 * 3. Run the example.<br>
 * 4. Go to <a href="https://mumbai.polygonscan.com/address/{wallet_address}#internaltx">scan page of a wallet address</a> to see the transaction log.<br>
 */
public class Main {

    private static final String USR_OP_STR = """
            Sender:               0x058bD457D52023d477202C9e9b857221578DE9A2,
            Nonce:                0x01,
            InitCode:             0x,
            CallData:             0xb61d27f6000000000000000000000000b56571c4944d65ceadd424bfcc08d155b7ecd4c900000000000000000000000000000000000000000000000000d529ae9e86000000000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000000000000000000000000000,
            CallGasLimit:         0x02a7e9,
            VerificationGasLimit: 0x101906,
            PreVerificationGas:   0x011170,
            MaxFeePerGas:         0x83dcb287,
            MaxPriorityFeePerGas: 0x77359400,
            PaymasterAndData:     0x,
            Signature:            0x,
                      """;

    private static final String PRIVATE_KEY_1 = "0x_replace_your_private_key_1";

    private static final String PRIVATE_KEY_2 = "0x_replace_your_private_key_1";

    private static final String BUNDLE_SERVICE_URL = "https://api.stackup.sh/v1/node/b69c55cba47271e3471c1e8d2c0fb9ddb79dfa4550aabf52c49ce9fbc272c093";

    private static UserOperation getUsrOpFromString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        Arrays.stream(USR_OP_STR.split(",")).map(it -> it.split(":")).forEach(entry -> {
            if (entry.length > 1) {
                String key = entry[0].trim();
                json.put(key.substring(0, 1).toLowerCase() + key.substring(1), entry[1].trim().replace("'", ""));
            }
        });
        return mapper.readValue(json.toPrettyString(), UserOperation.class);
    }

    public static void main(String[] args) {
        VoltaSessionKeyClient client = VoltaSdk.initSessionKeyClient(BUNDLE_SERVICE_URL);
        Single.create((SingleOnSubscribe<UserOperation>) emitter -> {
            UserOperation usrOp = getUsrOpFromString().copyToBuilder().setBlockchain(Blockchain.POLYGON_MUMBAI).build();
            emitter.onSuccess(usrOp);
        }).map(usrOp -> {
            usrOp.sign(PRIVATE_KEY_1, PRIVATE_KEY_2);
            return usrOp;
        }).flatMap(client::sendUserOperation).subscribe(hash -> {
            System.out.println("Send user operation success: " + hash);
        }, error -> {
            System.out.println("Failed to send user operation: " + error.getMessage());
            error.printStackTrace();
        });
    }
}
