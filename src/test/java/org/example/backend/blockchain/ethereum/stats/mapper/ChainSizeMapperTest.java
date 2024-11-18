package org.example.backend.blockchain.ethereum.stats.mapper;

import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSize;
import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSizeDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChainSizeMapperTest {

    @Test
    public void testToDto() {
        ChainSize chainSize = new ChainSize();
        chainSize.setBlockNumber("12345");
        chainSize.setChainTimeStamp("2023-01-01T00:00:00Z");
        chainSize.setChainSize("1000");
        chainSize.setClientType("geth");
        chainSize.setSyncMode("fast");

        ChainSizeDto dto = ChainSizeMapper.toDto(chainSize);

        assertEquals(chainSize.getBlockNumber(), dto.getBlockNumber());
        assertEquals(chainSize.getChainTimeStamp(), dto.getChainTimeStamp());
        assertEquals(chainSize.getChainSize(), dto.getChainSize());
        assertEquals(chainSize.getClientType(), dto.getClientType());
        assertEquals(chainSize.getSyncMode(), dto.getSyncMode());
    }

    @Test
    public void testToEntity() {
        ChainSizeDto dto = new ChainSizeDto();
        dto.setBlockNumber("12345");
        dto.setChainTimeStamp("2023-01-01T00:00:00Z");
        dto.setChainSize("1000");
        dto.setClientType("geth");
        dto.setSyncMode("fast");

        ChainSize chainSize = ChainSizeMapper.toEntity(dto);

        assertEquals(dto.getBlockNumber(), chainSize.getBlockNumber());
        assertEquals(dto.getChainTimeStamp(), chainSize.getChainTimeStamp());
        assertEquals(dto.getChainSize(), chainSize.getChainSize());
        assertEquals(dto.getClientType(), chainSize.getClientType());
        assertEquals(dto.getSyncMode(), chainSize.getSyncMode());
    }

    @Test
    public void testToDtoList() {
        ChainSize chainSize1 = new ChainSize();
        chainSize1.setBlockNumber("12345");
        chainSize1.setChainTimeStamp("2023-01-01T00:00:00Z");
        chainSize1.setChainSize("1000");
        chainSize1.setClientType("geth");
        chainSize1.setSyncMode("fast");

        ChainSize chainSize2 = new ChainSize();
        chainSize2.setBlockNumber("12345");
        chainSize2.setChainTimeStamp("2023-01-02T00:00:00Z");
        chainSize2.setChainSize("2000");
        chainSize2.setClientType("parity");
        chainSize2.setSyncMode("full");

        List<ChainSize> chainSizes = List.of(chainSize1, chainSize2);
        List<ChainSizeDto> dtos = ChainSizeMapper.toDtoList(chainSizes);

        assertEquals(chainSizes.size(), dtos.size());
        assertEquals(chainSizes.get(0).getBlockNumber(), dtos.get(0).getBlockNumber());
        assertEquals(chainSizes.get(1).getBlockNumber(), dtos.get(1).getBlockNumber());
    }

    @Test
    public void testToEntityList() {
        ChainSizeDto dto1 = new ChainSizeDto();
        dto1.setBlockNumber("12346");
        dto1.setChainTimeStamp("2023-01-01T00:00:00Z");
        dto1.setChainSize("1000");
        dto1.setClientType("geth");
        dto1.setSyncMode("fast");

        ChainSizeDto dto2 = new ChainSizeDto();
        dto2.setBlockNumber("12346");
        dto2.setChainTimeStamp("2023-01-02T00:00:00Z");
        dto2.setChainSize("2000");
        dto2.setClientType("parity");
        dto2.setSyncMode("full");

        List<ChainSizeDto> dtos = List.of(dto1, dto2);
        List<ChainSize> chainSizes = ChainSizeMapper.toEntityList(dtos);

        assertEquals(dtos.size(), chainSizes.size());
        assertEquals(dtos.get(0).getBlockNumber(), chainSizes.get(0).getBlockNumber());
        assertEquals(dtos.get(1).getBlockNumber(), chainSizes.get(1).getBlockNumber());
    }
}