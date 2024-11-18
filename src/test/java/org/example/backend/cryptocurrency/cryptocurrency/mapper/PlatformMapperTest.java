package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlatformMapperTest {

//    @Test
//    public void testToDto() {
//        Platform platform = Platform.builder()
//                .id(1L)
//                .platformId(1L)
//                .name("Ethereum")
//                .symbol("ETH")
//                .tokenAddress("0x123")
//                .build();
//
//        PlatformDto dto = PlatformMapper.toDto(platform);
//
//        assertNotNull(dto);
//        assertEquals(platform.getId(), dto.getId());
//        assertEquals(platform.getPlatformId(), dto.getPlatformId());
//        assertEquals(platform.getName(), dto.getName());
//        assertEquals(platform.getSymbol(), dto.getSymbol());
//        assertEquals(platform.getTokenAddress(), dto.getTokenAddress());
//    }

    @Test
    public void testToEntity() {
        PlatformDto dto = PlatformDto.builder()
                .id(1L)
                .platformId(1L)
                .name("Ethereum")
                .symbol("ETH")
                .tokenAddress("0x123")
                .build();

        Platform entity = PlatformMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getPlatformId(), entity.getPlatformId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getSymbol(), entity.getSymbol());
        assertEquals(dto.getTokenAddress(), entity.getTokenAddress());
    }
}