package org.example.backend.blockchain.ethereum.transaction.mapper;


import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;

public class EthereumTransactionMapper {

    public static EthereumTransactionDto mapTrasactionToTransactionDto(EthereumTransaction transaction) {
        return EthereumTransactionDto.builder()
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

    public static EthereumTransaction mapTrasactionDtoToTransaction(EthereumTransactionDto transactionDto) {
        return EthereumTransaction.builder()
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
