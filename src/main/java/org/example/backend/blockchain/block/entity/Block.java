package org.example.backend.blockchain.block.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.transaction.entity.Transaction;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Block {
    @Id
    private Long id;
    @OneToOne
    private BlockHeader header;
    private int transactionCount;
    @OneToMany
    private List<Transaction> transactions;

    public Block() {

    }
}
