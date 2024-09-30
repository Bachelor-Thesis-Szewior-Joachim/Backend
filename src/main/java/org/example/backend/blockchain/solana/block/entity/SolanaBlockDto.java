package org.example.backend.blockchain.solana.block.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.Transaction;

import java.util.List;

@Getter
@Setter
@Builder
public class SolanaBlockDto {

    private Long id;
    private SolanaBlockHeader header;
    private int transactionCount;
    private List<Transaction> transactions;
    private String blockNumber;
    private String slot;
    private String age;
    private String feeRecipient;
    private String gasUsed;
    private String gasLimit;
    private String baseFee;
    private String reward;
    private String burntFees;
}
