package org.example.backend.blockchain.solana.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.block.entity.rewards.RewardsDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaBlockDto {

    private String blockHeight;
    private String blockTime;
    private String blockHash;
    private String parentSlot;
    private String previousHash;
    private RewardsDto rewardsDto;

    private List<SolanaTransactionDto> transactions;
}
