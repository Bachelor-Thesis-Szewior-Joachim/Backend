package org.example.backend.blockchain.solana.block.mapper;

import org.example.backend.blockchain.solana.block.entity.SolanaBlock;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.example.backend.blockchain.solana.transaction.mapper.JsonSolanaTransactionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

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
//                .transactions(blockInfo.getTransactions())
//                        .stream().map(JsonSolanaTransactionMapper::mapTrasactionToTransactionDto)
//                        .collect(Collectors.toList()))
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
//                .transactions(blockInfoDto.getTransactions()
//                        .stream().map(JsonSolanaTransactionMapper::mapTrasactionDtoToTransaction)
//                        .collect(Collectors.toList()))
                .build();
    }

//    public static SolanaBlock mapJsonToBlock(String jsonResponse) {
//        try {
//            JsonNode rootNode = objectMapper.readTree(jsonResponse);
//            JsonNode resultNode = rootNode.path("result");
//
//            SolanaBlock solanaBlock = new SolanaBlock();
//            solanaBlock.setBlockHeight(resultNode.path("blockHeight").asText());
//            solanaBlock.setBlockTime(resultNode.path("blockTime").asText());
//            solanaBlock.setBlockHash(resultNode.path("blockhash").asText());
//            solanaBlock.setParentSlot(resultNode.path("parentSlot").asText());
//            solanaBlock.setPreviousHash(resultNode.path("previousBlockhash").asText());
//
//            // Map rewards
//            JsonNode rewardsNode = resultNode.path("rewards");
//            if (rewardsNode.isArray()) {
//                Rewards rewards = objectMapper.convertValue(
//                        rewardsNode,
//                        objectMapper.getTypeFactory().constructCollectionType(List.class, Rewards.class)
//                );
//                solanaBlock.setRewards(rewards);
//            }
//
//            // Map transactions
//            JsonNode transactionsNode = resultNode.path("transactions");
//            if (transactionsNode.isArray()) {
//                List<SolanaTransaction> transactions = transactionsNode
//                        .elements()
//                        .forEachRemaining(txNode -> {
//                            // Map transaction details using a separate mapper or inline logic
//                            SolanaTransaction transaction = mapJsonToTransaction(txNode);
//                            transactions.add(transaction);
//                        });
//                solanaBlock.setTransactions(transactions);
//            }
//
//            return solanaBlock;
//        } catch (Exception e) {
//            System.out.println("Error mapping JSON to SolanaBlock: " + e.getMessage());
//            return null;
//        }
//    }
}
