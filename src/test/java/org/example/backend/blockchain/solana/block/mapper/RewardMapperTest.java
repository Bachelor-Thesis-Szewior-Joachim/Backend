package org.example.backend.blockchain.solana.block.mapper;

import org.example.backend.blockchain.solana.block.entity.rewards.Rewards;
import org.example.backend.blockchain.solana.block.entity.rewards.RewardsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardMapperTest {

    @Test
    public void testToDto() {
        Rewards rewards = Rewards.builder()
                .commission(String.valueOf(5))
                .lamports(1000L)
                .postBalance(String.valueOf(2000L))
                .pubkey("somePubkey")
                .rewardType("someRewardType")
                .build();

        RewardsDto rewardsDto = RewardMapper.toDto(rewards);

        assertEquals(rewards.getCommission(), rewardsDto.getCommission());
        assertEquals(rewards.getLamports(), rewardsDto.getLamports());
        assertEquals(rewards.getPostBalance(), rewardsDto.getPostBalance());
        assertEquals(rewards.getPubkey(), rewardsDto.getPubkey());
        assertEquals(rewards.getRewardType(), rewardsDto.getRewardType());
    }

    @Test
    public void testToEntity() {
        RewardsDto rewardsDto = RewardsDto.builder()
                .commission(String.valueOf(5))
                .lamports(1000L)
                .postBalance(String.valueOf(2000L))
                .pubkey("somePubkey")
                .rewardType("someRewardType")
                .build();

        Rewards rewards = RewardMapper.toEntity(rewardsDto);

        assertEquals(rewardsDto.getCommission(), rewards.getCommission());
        assertEquals(rewardsDto.getLamports(), rewards.getLamports());
        assertEquals(rewardsDto.getPostBalance(), rewards.getPostBalance());
        assertEquals(rewardsDto.getPubkey(), rewards.getPubkey());
        assertEquals(rewardsDto.getRewardType(), rewards.getRewardType());
    }
}