package org.example.backend.blockchain.solana.transaction.mapper;


import org.example.backend.blockchain.solana.transaction.entity.Transaction;
import org.example.backend.blockchain.solana.transaction.entity.TransactionDto;

public class TransactionMapper {

    public static TransactionDto mapTrasactionToTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .blockchain(transaction.getBlockchain())
                .hash(transaction.getHash())
                .fromAddress(transaction.getFromAddress())
                .toAddress(transaction.getToAddress())
                .amount(transaction.getAmount())
                .method(transaction.getMethod())
                .blockNumber(transaction.getBlockNumber())
                .age(transaction.getAge())
                .transactionFee(transaction.getTransactionFee())
                .build();
    }

    public static Transaction mapTrasactionDtoToTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .blockchain(transactionDto.getBlockchain())
                .hash(transactionDto.getHash())
                .fromAddress(transactionDto.getFromAddress())
                .toAddress(transactionDto.getToAddress())
                .amount(transactionDto.getAmount())
                .method(transactionDto.getMethod())
                .blockNumber(transactionDto.getBlockNumber())
                .age(transactionDto.getAge())
                .transactionFee(transactionDto.getTransactionFee())
                .build();
    }
}
