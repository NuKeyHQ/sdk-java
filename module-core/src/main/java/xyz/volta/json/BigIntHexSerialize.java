package xyz.volta.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigInteger;

public class BigIntHexSerialize extends JsonSerializer<BigInteger> {

    @Override
    public void serialize(BigInteger value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeString("0x" + value.toString(16));
        }
    }
}
