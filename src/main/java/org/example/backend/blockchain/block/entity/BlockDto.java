package org.example.backend.blockchain.block.entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.transaction.entity.Transaction;

import java.util.List;

@Getter
@Setter
@Builder
public class BlockDto {

    private Long id;
    private BlockHeader header;
    private int transactionCount;
    private List<Transaction> transactions;
}
