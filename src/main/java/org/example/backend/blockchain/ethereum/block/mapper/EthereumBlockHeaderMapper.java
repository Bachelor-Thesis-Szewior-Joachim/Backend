package org.example.backend.blockchain.ethereum.block.mapper;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockHeader;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockHeaderDto;

public class EthereumBlockHeaderMapper {

    public static EthereumBlockHeader mapBlockHeaderDtoToBlockHeader(EthereumBlockHeaderDto blockHeaderDto) {
        return EthereumBlockHeader.builder()
                .id(blockHeaderDto.getId())
                .previousBlockHash(blockHeaderDto.getPreviousBlockHash())
                .difficultyTarget(blockHeaderDto.getDifficultyTarget())
                .timestamp(blockHeaderDto.getTimestamp())
                .merkleRoot(blockHeaderDto.getMerkleRoot())
                .nonce(blockHeaderDto.getNonce())
                .version(blockHeaderDto.getVersion())
                .build();
    }

    public static EthereumBlockHeaderDto mapBlockHeaderToBlockHeaderDto(EthereumBlockHeader blockHeader) {
        return EthereumBlockHeaderDto.builder()
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
