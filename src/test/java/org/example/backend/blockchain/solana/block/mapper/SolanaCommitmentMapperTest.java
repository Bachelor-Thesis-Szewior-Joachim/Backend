package org.example.backend.blockchain.solana.block.mapper;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaCommitmentMapperTest {

    @Test
    public void testMapJsonToValue() {
        String jsonResponse = "{ \"result\": { \"commitment\": \"finalized\", \"totalStake\": \"1000000\" } }";

        Map<String, String> result = SolanaCommitmentMapper.mapJsonToValue(jsonResponse);

        assertEquals("finalized", result.get("commitment"));
        assertEquals("1000000", result.get("totalStake"));
    }
}