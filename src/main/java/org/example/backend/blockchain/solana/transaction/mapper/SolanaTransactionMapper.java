package org.example.backend.blockchain.solana.transaction.mapper;


import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;

public class SolanaTransactionMapper {

    public static SolanaTransactionDto mapTrasactionToTransactionDto(SolanaTransaction transaction) {
        return SolanaTransactionDto.builder()
//                .id(transaction.getId())
//                .blockchain(transaction.getBlockchain())
//                .hash(transaction.getHash())
//                .fromAddress(transaction.getFromAddress())
//                .toAddress(transaction.getToAddress())
//                .amount(transaction.getAmount())
//                .method(transaction.getMethod())
//                .blockNumber(transaction.getBlockNumber())
//                .age(transaction.getAge())
//                .transactionFee(transaction.getTransactionFee())
                .build();
    }

    public static SolanaTransaction mapTrasactionDtoToTransaction(SolanaTransactionDto solanaTransactionDto) {
        return SolanaTransaction.builder()
//                .id(solanaTransactionDto.getId())
//                .blockchain(solanaTransactionDto.getBlockchain())
//                .hash(solanaTransactionDto.getHash())
//                .fromAddress(solanaTransactionDto.getFromAddress())
//                .toAddress(solanaTransactionDto.getToAddress())
//                .amount(solanaTransactionDto.getAmount())
//                .method(solanaTransactionDto.getMethod())
//                .blockNumber(solanaTransactionDto.getBlockNumber())
//                .age(solanaTransactionDto.getAge())
//                .transactionFee(solanaTransactionDto.getTransactionFee())
                .build();
    }
}
