package org.example.backend.blockchain.solana.block.mapper;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolanaValueMapperTest {

    @Test
    public void testMapJsonToValue() {
        String jsonResponse = "{ \"result\": { \"value\": { \"byIdentity\": { \"identity1\": [1, 2, 3], \"identity2\": [4, 5, 6] }, \"range\": { \"start\": 0, \"end\": 10 } } } }";

        Map<String, Object> result = SolanaValueMapper.mapJsonToValue(jsonResponse);

        assertNotNull(result);

        Map<String, List<Integer>> byIdentity = (Map<String, List<Integer>>) result.get("byIdentity");
        assertNotNull(byIdentity);
        assertEquals(List.of(1, 2, 3), byIdentity.get("identity1"));
        assertEquals(List.of(4, 5, 6), byIdentity.get("identity2"));

        Map<String, Integer> range = (Map<String, Integer>) result.get("range");
        assertNotNull(range);
        assertEquals(0, range.get("start"));
        assertEquals(10, range.get("end"));
    }
}