package org.example.backend.blockchain.block.mapper;

import org.example.backend.blockchain.block.entity.Block;
import org.example.backend.blockchain.block.entity.BlockDto;

public class BlockMapper {

    public static Block mapBlockDtoToBlock(BlockDto blockDto) {

        return Block.builder()
                .id(blockDto.getId())
                .header(blockDto.getHeader())
                .transactionCount(blockDto.getTransactionCount())
                .transactions(blockDto.getTransactions())
                .build();
    }

    public static BlockDto mapBlockToBlockDto(Block block) {

        return BlockDto.builder()
                .id(block.getId())
                .header(block.getHeader())
                .transactionCount(block.getTransactionCount())
                .transactions(block.getTransactions())
                .build();
    }
}
