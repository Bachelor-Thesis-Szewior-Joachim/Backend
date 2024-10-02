package org.example.backend.blockchain.ethereum.block.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumBlock {
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
    private List<EthereumTransaction> transactions;

    public EthereumBlock() {

    }
}
