package org.example.backend.blockchain.solana.transaction.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessage;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.Header;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class JsonSolanaTransactionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SolanaTransaction mapJsonToSolanaTransaction(String jsonResponse) {
        JsonNode rootNode = null;
        JsonNode resultNode = null;
        try {
            rootNode = objectMapper.readTree(jsonResponse);
            resultNode = rootNode.path("result");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Extract blockTime and slot
        String blockTime = resultNode.path("blockTime").asText();
        String slot = resultNode.path("slot").asText();

        // Extract signatures
        List<String> signatures = new ArrayList<>();
        JsonNode signaturesNode = resultNode.path("transaction").path("signatures");
        for (JsonNode signatureNode : signaturesNode) {
            signatures.add(signatureNode.asText());
        }

        JsonNode messageNode = resultNode.path("transaction").path("message");

        SolanaTransactionMessage message = SolanaTransactionMessage.builder()
                .accountKeys(getAccountKeys(messageNode))
                .header(getHeader(messageNode.path("header")))
                .recentBlockHash(messageNode.path("recentBlockhash").asText())
                .instructions(getInstructions(messageNode.path("instructions")))
                .blockHash(messageNode.path("blockHash").asText())
                .build();

        return SolanaTransaction.builder()
                .blockTime(blockTime)
                .slot(slot)
                .signatures(signatures)
                .message(message)
                .build();
    }

    private static List<String> getAccountKeys(JsonNode messageNode) {
        List<String> accountKeys = new ArrayList<>();
        JsonNode accountKeysNode = messageNode.path("accountKeys");
        for (JsonNode accountKeyNode : accountKeysNode) {
            accountKeys.add(accountKeyNode.asText());
        }
        return accountKeys;
    }

    private static Header getHeader(JsonNode headerNode) {
        return Header.builder()
                .numReadonlySignedAccounts(headerNode.path("numReadonlySignedAccounts").asLong())
                .numReadonlyUnsignedAccounts(headerNode.path("numReadonlyUnsignedAccounts").asLong())
                .numRequiredSignatures(headerNode.path("numRequiredSignatures").asLong())
                .build();
    }

    private static List<Instruction> getInstructions(JsonNode instructionsNode) {
        List<Instruction> instructions = new ArrayList<>();
        for (JsonNode instructionNode : instructionsNode) {
            Instruction instruction = Instruction.builder()
                    .programIdIndex(instructionNode.path("programIdIndex").asText())
                    .accounts(getAccounts(instructionNode.path("accounts")))
                    .data(instructionNode.path("data").asText())
                    .stackHeight(instructionNode.path("stackHeight").isNull() ? null : instructionNode.path("stackHeight").asInt())
                    .build();
            instructions.add(instruction);
        }
        return instructions;
    }

    private static List<String> getAccounts(JsonNode accountsNode) {
        List<String> accounts = new ArrayList<>();
        for (JsonNode accountNode : accountsNode) {
            accounts.add(accountNode.asText());
        }
        return accounts;
    }
}
