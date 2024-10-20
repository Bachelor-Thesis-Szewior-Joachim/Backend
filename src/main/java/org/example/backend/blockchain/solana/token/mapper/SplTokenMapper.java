package org.example.backend.blockchain.solana.token.mapper;

import org.example.backend.blockchain.solana.token.entity.splToken.SplToken;
import org.example.backend.blockchain.solana.token.entity.splToken.SplTokenDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SplTokenMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SplTokenDto toDto(SplToken splToken) {
        return SplTokenDto.builder()
                .amount(splToken.getAmount())
                .decimals(splToken.getDecimals())
                .uiAmount(splToken.getUiAmount())
                .uiAmountString(splToken.getUiAmountString())
                .build();
    }

    // Convert from DTO to entity
    public static SplToken toEntity(SplTokenDto splTokenDto) {
        return SplToken.builder()
                .amount(splTokenDto.getAmount())
                .decimals(splTokenDto.getDecimals())
                .uiAmount(splTokenDto.getUiAmount())
                .uiAmountString(splTokenDto.getUiAmountString())
                .build();
    }

    public static SplToken mapJsonToSplToken(String jsonResponse) {
        // Read the JSON response as a tree
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Navigate to the "value" node
        JsonNode valueNode = rootNode.path("result").path("value");

        // Map fields to SplToken
        return SplToken.builder()
                .amount(valueNode.path("amount").asText())
                .decimals(valueNode.path("decimals").asLong())
                .uiAmount(valueNode.path("uiAmount").asText())
                .uiAmountString(valueNode.path("uiAmountString").asText())
                .build();
    }
}
