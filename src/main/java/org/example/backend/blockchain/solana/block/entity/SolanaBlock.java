package org.example.backend.blockchain.solana.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaBlock {

    private int transactionCount;
    private String blockNumber;
    private String slot;
    private String age;
    private String feeRecipient;
    private String gasUsed;
    private String gasLimit;
    private String baseFee;
    private String reward;
    private String burntFees;
    private List<SolanaTransaction> transactions;
    public SolanaBlock() {

    }
}
