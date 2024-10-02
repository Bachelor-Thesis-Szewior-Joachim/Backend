package org.example.backend.blockchain.solana.block.mapper;

import org.example.backend.blockchain.solana.block.entity.SolanaBlock;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;

public class SolanaBlockMapper {

    public static SolanaBlock mapBlockDtoToBlock(SolanaBlockDto blockDto) {

        return SolanaBlock.builder()
                .transactionCount(blockDto.getTransactionCount())
                .transactions(blockDto.getTransactions())
                .blockNumber(blockDto.getBlockNumber())
                .slot(blockDto.getSlot())
                .age(blockDto.getAge())
                .age(blockDto.getAge())
                .feeRecipient(blockDto.getFeeRecipient())
                .gasUsed(blockDto.getGasUsed())
                .gasLimit(blockDto.getGasLimit())
                .baseFee(blockDto.getBaseFee())
                .reward(blockDto.getReward())
                .burntFees(blockDto.getBurntFees())
                .build();
    }

    public static SolanaBlockDto mapBlockToBlockDto(SolanaBlock block) {

        return SolanaBlockDto.builder()
                .transactionCount(block.getTransactionCount())
                .transactions(block.getTransactions())
                .blockNumber(block.getBlockNumber())
                .slot(block.getSlot())
                .age(block.getAge())
                .age(block.getAge())
                .feeRecipient(block.getFeeRecipient())
                .gasUsed(block.getGasUsed())
                .gasLimit(block.getGasLimit())
                .baseFee(block.getBaseFee())
                .reward(block.getReward())
                .burntFees(block.getBurntFees())
                .build();
    }
}
