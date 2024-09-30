package org.example.backend.blockchain.solana.block.mapper;

import org.example.backend.blockchain.solana.block.entity.SolanaBlockHeader;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockHeaderDto;

public class SolanaBlockHeaderMapper {

    public static SolanaBlockHeader mapBlockHeaderDtoToBlockHeader(SolanaBlockHeaderDto blockHeaderDto) {
        return SolanaBlockHeader.builder()
                .id(blockHeaderDto.getId())
                .previousBlockHash(blockHeaderDto.getPreviousBlockHash())
                .difficultyTarget(blockHeaderDto.getDifficultyTarget())
                .timestamp(blockHeaderDto.getTimestamp())
                .merkleRoot(blockHeaderDto.getMerkleRoot())
                .nonce(blockHeaderDto.getNonce())
                .version(blockHeaderDto.getVersion())
                .build();
    }

    public static SolanaBlockHeaderDto mapBlockHeaderToBlockHeaderDto(SolanaBlockHeader blockHeader) {
        return SolanaBlockHeaderDto.builder()
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
