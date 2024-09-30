package org.example.backend.blockchain.ethereum.block.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;

import java.util.List;

@Getter
@Setter
@Builder
public class EthereumBlockDto {

    private Long id;
    private EthereumBlockHeader header;
    private int transactionCount;
    private List<EthereumTransaction> transactions;
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
