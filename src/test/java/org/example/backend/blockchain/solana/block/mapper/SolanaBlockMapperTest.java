package org.example.backend.blockchain.solana.block.mapper;


import org.example.backend.blockchain.solana.block.entity.SolanaBlock;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.example.backend.blockchain.solana.block.entity.rewards.Rewards;
import org.example.backend.blockchain.solana.block.entity.rewards.RewardsDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaBlockMapperTest {

    @Test
    public void testToDto() {
        Rewards rewards = Rewards.builder()
                .commission("5")
                .lamports(1000L)
                .postBalance("2000")
                .pubkey("somePubkey")
                .rewardType("someRewardType")
                .build();

        SolanaBlock block = SolanaBlock.builder()
                .blockHeight("12345")
                .blockTime("2023-01-01T00:00:00Z")
                .blockHash("someBlockHash")
                .parentSlot("12344")
                .previousHash("somePreviousHash")
                .rewards(rewards)
                .transactions(List.of("tx1", "tx2"))
                .build();

        SolanaBlockDto blockDto = SolanaBlockMapper.toDto(block);

        assertEquals(block.getBlockHeight(), blockDto.getBlockHeight());
        assertEquals(block.getBlockTime(), blockDto.getBlockTime());
        assertEquals(block.getBlockHash(), blockDto.getBlockHash());
        assertEquals(block.getParentSlot(), blockDto.getParentSlot());
        assertEquals(block.getPreviousHash(), blockDto.getPreviousHash());
        assertEquals(block.getRewards().getCommission(), blockDto.getRewardsDto().getCommission());
        assertEquals(block.getTransactions(), blockDto.getTransactions());
    }

    @Test
    public void testToEntity() {
        RewardsDto rewardsDto = RewardsDto.builder()
                .commission("5")
                .lamports(1000L)
                .postBalance("2000")
                .pubkey("somePubkey")
                .rewardType("someRewardType")
                .build();

        SolanaBlockDto blockDto = SolanaBlockDto.builder()
                .blockHeight("12345")
                .blockTime("2023-01-01T00:00:00Z")
                .blockHash("someBlockHash")
                .parentSlot("12344")
                .previousHash("somePreviousHash")
                .rewardsDto(rewardsDto)
                .transactions(List.of("tx1", "tx2"))
                .build();

        SolanaBlock block = SolanaBlockMapper.toEntity(blockDto);

        assertEquals(blockDto.getBlockHeight(), block.getBlockHeight());
        assertEquals(blockDto.getBlockTime(), block.getBlockTime());
        assertEquals(blockDto.getBlockHash(), block.getBlockHash());
        assertEquals(blockDto.getParentSlot(), block.getParentSlot());
        assertEquals(blockDto.getPreviousHash(), block.getPreviousHash());
        assertEquals(blockDto.getRewardsDto().getCommission(), block.getRewards().getCommission());
        assertEquals(blockDto.getTransactions(), block.getTransactions());
    }

    @Test
    public void testMapJsonToSolanaBlock() throws Exception {
        String jsonResponse = "{ \"result\": { \"blockHeight\": \"12345\", \"blockTime\": \"2023-01-01T00:00:00Z\", \"blockhash\": \"someBlockHash\", \"parentSlot\": \"12344\", \"previousBlockhash\": \"somePreviousHash\", \"rewards\": [{ \"commission\": \"5\", \"lamports\": 1000, \"postBalance\": \"2000\", \"pubkey\": \"somePubkey\", \"rewardType\": \"someRewardType\" }], \"transactions\": [{ \"transaction\": { \"signatures\": [\"tx1\", \"tx2\"] } }] } }";
        SolanaBlock block = SolanaBlockMapper.mapJsonToSolanaBlock(jsonResponse);

        assertEquals("12345", block.getBlockHeight());
        assertEquals("2023-01-01T00:00:00Z", block.getBlockTime());
        assertEquals("someBlockHash", block.getBlockHash());
        assertEquals("12344", block.getParentSlot());
        assertEquals("somePreviousHash", block.getPreviousHash());
        assertEquals("5", block.getRewards().getCommission());
        assertEquals(1000L, block.getRewards().getLamports());
        assertEquals("2000", block.getRewards().getPostBalance());
        assertEquals("somePubkey", block.getRewards().getPubkey());
        assertEquals("someRewardType", block.getRewards().getRewardType());
        assertEquals(List.of("tx1", "tx2"), block.getTransactions());
    }
}