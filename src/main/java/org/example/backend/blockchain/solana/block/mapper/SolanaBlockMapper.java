package org.example.backend.blockchain.solana.block.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.backend.blockchain.solana.block.entity.SolanaBlock;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.example.backend.blockchain.solana.block.entity.rewards.Rewards;
import org.example.backend.blockchain.solana.transaction.mapper.JsonSolanaTransactionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolanaBlockMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SolanaBlockDto toDto(SolanaBlock blockInfo) {
        return SolanaBlockDto.builder()
                .blockHeight(blockInfo.getBlockHeight())
                .blockTime(blockInfo.getBlockTime())
                .blockHash(blockInfo.getBlockHash())
                .parentSlot(blockInfo.getParentSlot())
                .previousHash(blockInfo.getPreviousHash())
                .rewardsDto(RewardMapper.toDto(blockInfo.getRewards()))
                .transactions(blockInfo.getTransactions())
                .build();
    }

    public static SolanaBlock toEntity(SolanaBlockDto blockInfoDto) {
        return SolanaBlock.builder()
                .blockHeight(blockInfoDto.getBlockHeight())
                .blockTime(blockInfoDto.getBlockTime())
                .blockHash(blockInfoDto.getBlockHash())
                .parentSlot(blockInfoDto.getParentSlot())
                .previousHash(blockInfoDto.getPreviousHash())
                .rewards(RewardMapper.toEntity(blockInfoDto.getRewardsDto()))
                .transactions(blockInfoDto.getTransactions())
                .build();
    }

    public static SolanaBlock mapJsonToSolanaBlock(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode.path("result");

            // Extract block fields
            String blockHeight = resultNode.path("blockHeight").asText();
            String blockTime = resultNode.path("blockTime").asText();
            String blockHash = resultNode.path("blockhash").asText();
            String parentSlot = resultNode.path("parentSlot").asText();
            String previousHash = resultNode.path("previousBlockhash").asText();

            // Extract rewards (assuming a single reward or handle multiple as needed)
            JsonNode rewardsNode = resultNode.path("rewards").get(0);
            Rewards rewards = null;
            if (rewardsNode != null) {
                rewards = Rewards.builder()
                        .commission(rewardsNode.path("commission").asText(null))
                        .lamports(rewardsNode.path("lamports").asLong())
                        .postBalance(rewardsNode.path("postBalance").asText())
                        .pubkey(rewardsNode.path("pubkey").asText())
                        .rewardType(rewardsNode.path("rewardType").asText())
                        .build();
            }

            List<String> transactionsSignature = new ArrayList<>();
            JsonNode transactionsNode = resultNode.path("transactions");
            for (JsonNode transactionNode : transactionsNode) {
                JsonNode signaturesNode = transactionNode.path("transaction").path("signatures");
                for (JsonNode signatureNode : signaturesNode) {
                    transactionsSignature.add(signatureNode.asText());
                }
            }

            return SolanaBlock.builder()
                    .blockHeight(blockHeight)
                    .blockTime(blockTime)
                    .blockHash(blockHash)
                    .parentSlot(parentSlot)
                    .previousHash(previousHash)
                    .rewards(rewards)
                    .transactions(transactionsSignature)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to map JSON to SolanaBlock: " + e.getMessage(), e);
        }
    }
}
