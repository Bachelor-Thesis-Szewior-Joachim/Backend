package org.example.backend.blockchain.solana.transaction.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonSolanaSignatureMapper {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> mapJsonToSolanaSignature(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode valueNode = rootNode.path("result").path("value");
            String confirmationStatus = valueNode.path("confirmationStatus").asText();
            String slot = valueNode.get("slot").asText();
            String status = valueNode.get("status").asText();

            Map<String, String> map = new HashMap<>();
            map.put("confirmationStatus", confirmationStatus);
            map.put("slot", slot);
            map.put("status", status);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
