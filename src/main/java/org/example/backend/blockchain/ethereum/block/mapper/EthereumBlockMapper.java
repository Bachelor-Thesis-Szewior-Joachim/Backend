package org.example.backend.blockchain.ethereum.block.mapper;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlock;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;

public class EthereumBlockMapper {

    public static EthereumBlock mapBlockDtoToBlock(EthereumBlockDto blockDto) {

        return EthereumBlock.builder()
                .id(blockDto.getId())
                .header(blockDto.getHeader())
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

    public static EthereumBlockDto mapBlockToBlockDto(EthereumBlock block) {

        return EthereumBlockDto.builder()
                .id(block.getId())
                .header(block.getHeader())
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
