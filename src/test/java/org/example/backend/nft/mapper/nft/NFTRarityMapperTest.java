package org.example.backend.nft.mapper.nft;

import org.example.backend.nft.entity.nft.rarity.NFTRarity;
import org.example.backend.nft.entity.nft.rarity.NFTRarityDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NFTRarityMapperTest {

    @Test
    public void testToDto() {
        NFTRarity entity = NFTRarity.builder()
                .strategyVersion("v1")
                .rank(1)
                .score(100.0)
                .calculatedAt("2023-10-01")
                .maxRank(10)
                .totalSupply(100)
                .uniqueAttributeCount(5)
                .build();

        NFTRarityDto dto = NFTRarityMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getStrategyVersion(), dto.getStrategyVersion());
        assertEquals(entity.getRank(), dto.getRank());
        assertEquals(entity.getScore(), dto.getScore());
        assertEquals(entity.getCalculatedAt(), dto.getCalculatedAt());
        assertEquals(entity.getMaxRank(), dto.getMaxRank());
        assertEquals(entity.getTotalSupply(), dto.getTotalSupply());
        assertEquals(entity.getUniqueAttributeCount(), dto.getUniqueAttributeCount());
    }

    @Test
    public void testToEntity() {
        NFTRarityDto dto = NFTRarityDto.builder()
                .strategyVersion("v1")
                .rank(1)
                .score(100.0)
                .calculatedAt("2023-10-01")
                .maxRank(10)
                .totalSupply(100)
                .uniqueAttributeCount(5)
                .build();

        NFTRarity entity = NFTRarityMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getStrategyVersion(), entity.getStrategyVersion());
        assertEquals(dto.getRank(), entity.getRank());
        assertEquals(dto.getScore(), entity.getScore());
        assertEquals(dto.getCalculatedAt(), entity.getCalculatedAt());
        assertEquals(dto.getMaxRank(), entity.getMaxRank());
        assertEquals(dto.getTotalSupply(), entity.getTotalSupply());
        assertEquals(dto.getUniqueAttributeCount(), entity.getUniqueAttributeCount());
    }
}