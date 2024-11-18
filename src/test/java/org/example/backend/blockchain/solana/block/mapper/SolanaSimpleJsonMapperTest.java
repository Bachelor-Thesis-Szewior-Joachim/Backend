package org.example.backend.blockchain.solana.block.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaSimpleJsonMapperTest {

    @Test
    public void testMapJsonToResult() {
        String jsonResponse = "{ \"result\": { \"key\": \"value\" } }";
        String expected = "{\"key\":\"value\"}";

        String result = SolanaSimpleJsonMapper.mapJsonToResult(jsonResponse);

        assertEquals(expected, result);
    }

    @Test
    public void testMapJsonToValue() {
        String jsonResponse = "{ \"result\": { \"value\": \"expectedValue\" } }";
        String expected = "expectedValue";

        String result = SolanaSimpleJsonMapper.mapJsonToValue(jsonResponse);

        assertEquals(expected, result);
    }
}