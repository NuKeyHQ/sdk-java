package xyz.volta.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigInteger;

public class BigIntHexDeserialize extends JsonDeserializer<BigInteger> {
    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        try {
            return p.readValueAs(BigInteger.class);
        } catch (Exception e) {
            try {
                return new BigInteger(p.readValueAs(String.class).replace("0x", ""), 16);
            } catch (Exception ignored) {}
        }
        return BigInteger.ZERO;
    }
}
