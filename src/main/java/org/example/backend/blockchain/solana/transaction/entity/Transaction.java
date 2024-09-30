package org.example.backend.blockchain.solana.transaction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transaction {
    @Id
    private Long id;
    private String blockchain;
    private String hash;
    private String fromAddress;
    private String toAddress;
    private double amount;
    private String method;
    private Long blockNumber;
    private String age;
    private double transactionFee;

    public Transaction() {

    }
}
