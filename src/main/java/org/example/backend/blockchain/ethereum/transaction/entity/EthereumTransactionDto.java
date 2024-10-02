package org.example.backend.blockchain.ethereum.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumTransactionDto {
    private String blockchain;
    private String hash;
    private String fromAddress;
    private String toAddress;
    private double amount;
    private String method;
    private Long blockNumber;
    private String age;
    private double transactionFee;

}
