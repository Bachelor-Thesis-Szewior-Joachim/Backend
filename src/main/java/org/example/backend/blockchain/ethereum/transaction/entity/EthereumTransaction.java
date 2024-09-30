package org.example.backend.blockchain.ethereum.transaction.entity;


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
public class EthereumTransaction {
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

    public EthereumTransaction() {

    }
}
