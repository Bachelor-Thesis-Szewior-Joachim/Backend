package org.example.backend.blockchain.solana.transaction.mapper;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonSolanaSignatureMapperTest {

    @Test
    public void testMapJsonToSolanaSignature() throws Exception {
        String jsonResponse = "{ \"result\": { \"value\": [ { \"confirmationStatus\": \"confirmed\", \"slot\": \"12345\", \"status\": { \"Ok\": \"success\" } } ] } }";

        Map<String, String> result = JsonSolanaSignatureMapper.mapJsonToSolanaSignature(jsonResponse);

        assertNotNull(result);
        assertEquals("confirmed", result.get("confirmationStatus"));
        assertEquals("12345", result.get("slot"));
        assertEquals("success", result.get("status"));
    }
}