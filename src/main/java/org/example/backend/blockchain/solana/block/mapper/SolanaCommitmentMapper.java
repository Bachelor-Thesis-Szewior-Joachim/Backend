package org.example.backend.blockchain.solana.block.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class SolanaCommitmentMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static final Map<String, String> mapJsonToValue(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode valueNode = rootNode.path("result");

            Map<String, String> commitmentMap = new HashMap<>();
            String commitmentValue = valueNode.path("commitment").asText();
            String stakeValue = valueNode.path("totalStake").asText();
            commitmentMap.put("commitment", commitmentValue);
            commitmentMap.put("totalStake", stakeValue);
            return commitmentMap;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
