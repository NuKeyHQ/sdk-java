package xyz.volta.internal.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.math.BigInteger;

public class BigIntHexDeserialize extends JsonDeserializer<BigInteger> {
  @Override
  public BigInteger deserialize(JsonParser parser, DeserializationContext context) {
    try {
      return parser.readValueAs(BigInteger.class);
    } catch (Exception e) {
      try {
        return new BigInteger(parser.readValueAs(String.class).replace("0x", ""), 16);
      } catch (Exception ignored) {
      }
    }
    return BigInteger.ZERO;
  }
}
