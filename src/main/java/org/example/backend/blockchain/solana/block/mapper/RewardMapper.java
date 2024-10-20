package org.example.backend.blockchain.solana.block.mapper;

import org.example.backend.blockchain.solana.block.entity.rewards.Rewards;
import org.example.backend.blockchain.solana.block.entity.rewards.RewardsDto;

public class RewardMapper {

    public static RewardsDto toDto(Rewards rewards) {
        return RewardsDto.builder()
                .commission(rewards.getCommission())
                .lamports(rewards.getLamports())
                .postBalance(rewards.getPostBalance())
                .pubkey(rewards.getPubkey())
                .rewardType(rewards.getRewardType())
                .build();
    }

    public static Rewards toEntity(RewardsDto rewardsDto) {
        return Rewards.builder()
                .commission(rewardsDto.getCommission())
                .lamports(rewardsDto.getLamports())
                .postBalance(rewardsDto.getPostBalance())
                .pubkey(rewardsDto.getPubkey())
                .rewardType(rewardsDto.getRewardType())
                .build();
    }
}