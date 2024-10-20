package org.example.backend.blockchain.solana.token.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccount;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccountDto;

public class SolanaTokenMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SolanaTokenAccountDto toDto(SolanaTokenAccount entity) {
        return SolanaTokenAccountDto.builder()
                .pubkey(entity.getPubkey())
                .executable(entity.isExecutable())
                .lamports(entity.getLamports())
                .owner(entity.getOwner())
                .rentEpoch(entity.getRentEpoch())
                .space(entity.getSpace())
                .program(entity.getProgram())
                .dataSpace(entity.getDataSpace())
                .isNative(entity.isNative())
                .mint(entity.getMint())
                .accountOwner(entity.getAccountOwner())
                .state(entity.getState())
                .amount(entity.getAmount())
                .decimals(entity.getDecimals())
                .uiAmount(entity.getUiAmount())
                .uiAmountString(entity.getUiAmountString())
                .type(entity.getType())
                .build();
    }

    public static SolanaTokenAccount toEntity(SolanaTokenAccountDto dto) {
        return SolanaTokenAccount.builder()
                .pubkey(dto.getPubkey())
                .executable(dto.isExecutable())
                .lamports(dto.getLamports())
                .owner(dto.getOwner())
                .rentEpoch(dto.getRentEpoch())
                .space(dto.getSpace())
                .program(dto.getProgram())
                .dataSpace(dto.getDataSpace())
                .isNative(dto.isNative())
                .mint(dto.getMint())
                .accountOwner(dto.getAccountOwner())
                .state(dto.getState())
                .amount(dto.getAmount())
                .decimals(dto.getDecimals())
                .uiAmount(dto.getUiAmount())
                .uiAmountString(dto.getUiAmountString())
                .type(dto.getType())
                .build();
    }

    public static SolanaTokenAccount mapJsonToSolanaTokenAccount(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode valueNode = rootNode.path("result").path("value");

            if (valueNode.isArray() && valueNode.size() > 0) {
                JsonNode firstValueNode = valueNode.get(0); // Access the first element of the array
                JsonNode accountNode = firstValueNode.path("account");
                JsonNode dataNode = accountNode.path("data");
                JsonNode parsedNode = dataNode.path("parsed");
                JsonNode infoNode = parsedNode.path("info");
                JsonNode tokenAmountNode = infoNode.path("tokenAmount");

                return SolanaTokenAccount.builder()
                        .pubkey(firstValueNode.path("pubkey").asText())
                        .executable(accountNode.path("executable").asBoolean())
                        .lamports(accountNode.path("lamports").asLong())
                        .owner(accountNode.path("owner").asText())
                        .rentEpoch(accountNode.path("rentEpoch").asText())
                        .space(accountNode.path("space").asInt())
                        .program(dataNode.path("program").asText())
                        .dataSpace(dataNode.path("space").asInt())
                        .isNative(infoNode.path("isNative").asBoolean())
                        .mint(infoNode.path("mint").asText())
                        .accountOwner(infoNode.path("owner").asText())
                        .state(infoNode.path("state").asText())
                        .amount(tokenAmountNode.path("amount").asText())
                        .decimals(tokenAmountNode.path("decimals").asInt())
                        .uiAmount(tokenAmountNode.path("uiAmount").asDouble())
                        .uiAmountString(tokenAmountNode.path("uiAmountString").asText())
                        .type(parsedNode.path("type").asText())
                        .build();
            } else {
                throw new RuntimeException("Value array is empty or not found.");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }
}
