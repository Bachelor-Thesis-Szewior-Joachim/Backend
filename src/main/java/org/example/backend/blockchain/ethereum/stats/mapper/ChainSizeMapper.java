package org.example.backend.blockchain.ethereum.stats.mapper;

import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSize;
import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSizeDto;

import java.util.List;
import java.util.stream.Collectors;

public class ChainSizeMapper {

    public static ChainSizeDto toDto(ChainSize chainSize) {
        ChainSizeDto dto = new ChainSizeDto();
        dto.setBlockNumber(chainSize.getBlockNumber());
        dto.setChainTimeStamp(chainSize.getChainTimeStamp());
        dto.setChainSize(chainSize.getChainSize());
        dto.setClientType(chainSize.getClientType());
        dto.setSyncMode(chainSize.getSyncMode());
        return dto;
    }

    public static ChainSize toEntity(ChainSizeDto dto) {
        ChainSize chainSize = new ChainSize();
        chainSize.setBlockNumber(dto.getBlockNumber());
        chainSize.setChainTimeStamp(dto.getChainTimeStamp());
        chainSize.setChainSize(dto.getChainSize());
        chainSize.setClientType(dto.getClientType());
        chainSize.setSyncMode(dto.getSyncMode());
        return chainSize;
    }

    public static List<ChainSizeDto> toDtoList(List<ChainSize> chainSizes) {
        return chainSizes.stream().map(ChainSizeMapper::toDto).collect(Collectors.toList());
    }

    public static List<ChainSize> toEntityList(List<ChainSizeDto> dtos) {
        return dtos.stream().map(ChainSizeMapper::toEntity).collect(Collectors.toList());
    }
}
