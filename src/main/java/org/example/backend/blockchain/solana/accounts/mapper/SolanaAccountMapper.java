package org.example.backend.blockchain.solana.accounts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccount;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;

import java.io.IOException;

public class SolanaAccountMapper {
    public static SolanaAccount mapAccountDtoToAccount(SolanaAccountDto accountDto) {
        return SolanaAccount.builder()
                .lamports(accountDto.getLamports())
                .slot(accountDto.getSlot())
                .owner(accountDto.getOwner())
                .data(accountDto.getData())
                .coding(accountDto.getCoding())
                .executable(accountDto.isExecutable())
                .rentEpoch(accountDto.getRentEpoch())
                .space(accountDto.getSpace())
                .build();
    }

    public static SolanaAccountDto mapAccountToAccountDto(SolanaAccount account) {
        return SolanaAccountDto.builder()
                .lamports(account.getLamports())
                .slot(account.getSlot())
                .owner(account.getOwner())
                .data(account.getData())
                .coding(account.getCoding())
                .executable(account.isExecutable())
                .rentEpoch(account.getRentEpoch())
                .space(account.getSpace())
                .build();

    }

    public static SolanaAccount mapToSolanaAccount(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode.path("result");
            JsonNode contextNode = resultNode.path("context");
            JsonNode valueNode = resultNode.path("value");

            SolanaAccount account = new SolanaAccount();
            account.setLamports(valueNode.path("lamports").asLong());
            account.setSlot(contextNode.path("slot").asLong());
            account.setOwner(valueNode.path("owner").asText());
            account.setData(valueNode.path("data").asText());
            account.setExecutable(valueNode.path("executable").asBoolean());
            account.setRentEpoch(valueNode.path("rentEpoch").asText());
            account.setSpace(valueNode.path("space").asLong());

            // Assuming "state" and "coding" are derived from other values or not directly in the JSON
            account.setState(null); // Placeholder for actual logic if needed
            account.setCoding(null); // Placeholder for actual logic if needed

            return account;
        } catch (IOException e) {
            throw new RuntimeException("Error mapping JSON to SolanaAccount", e);
        }
    }
}
