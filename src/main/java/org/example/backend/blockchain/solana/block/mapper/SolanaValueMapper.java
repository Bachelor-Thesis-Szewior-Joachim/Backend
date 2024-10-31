package org.example.backend.blockchain.solana.block.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolanaValueMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> mapJsonToValue(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode valueNode = rootNode.path("result").path("value");

            Map<String, Object> result = new HashMap<>();

            JsonNode byIdentityNode = valueNode.path("byIdentity");
            Map<String, List<Integer>> byIdentity = new HashMap<>();
            byIdentityNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                List<Integer> values = objectMapper.convertValue(
                        entry.getValue(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class)
                );
                byIdentity.put(key, values);
            });
            result.put("byIdentity", byIdentity);

            JsonNode rangeNode = valueNode.path("range");
            Map<String, Integer> range = new HashMap<>();
            rangeNode.fields().forEachRemaining(entry -> {
                range.put(entry.getKey(), entry.getValue().asInt());
            });
            result.put("range", range);

            return result;
        } catch (Exception e) {
            System.out.println("Error mapping JSON to value: " + e.getMessage());
            return null;
        }
    }
}
