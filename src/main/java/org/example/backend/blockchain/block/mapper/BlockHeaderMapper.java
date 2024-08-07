package org.example.backend.blockchain.block.mapper;

import org.example.backend.blockchain.block.entity.BlockHeader;
import org.example.backend.blockchain.block.entity.BlockHeaderDto;

public class BlockHeaderMapper {

    public static BlockHeader mapBlockHeaderDtoToBlockHeader(BlockHeaderDto blockHeaderDto) {
        return BlockHeader.builder()
                .id(blockHeaderDto.getId())
                .previousBlockHash(blockHeaderDto.getPreviousBlockHash())
                .difficultyTarget(blockHeaderDto.getDifficultyTarget())
                .timestamp(blockHeaderDto.getTimestamp())
                .merkleRoot(blockHeaderDto.getMerkleRoot())
                .nonce(blockHeaderDto.getNonce())
                .version(blockHeaderDto.getVersion())
                .build();
    }

    public static BlockHeaderDto mapBlockHeaderToBlockHeaderDto(BlockHeader blockHeader) {
        return BlockHeaderDto.builder()
                .id(blockHeader.getId())
                .previousBlockHash(blockHeader.getPreviousBlockHash())
                .difficultyTarget(blockHeader.getDifficultyTarget())
                .timestamp(blockHeader.getTimestamp())
                .merkleRoot(blockHeader.getMerkleRoot())
                .nonce(blockHeader.getNonce())
                .version(blockHeader.getVersion())
                .build();
    }
}
