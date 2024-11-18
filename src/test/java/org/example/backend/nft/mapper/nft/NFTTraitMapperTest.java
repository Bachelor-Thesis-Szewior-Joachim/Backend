package org.example.backend.nft.mapper.nft;

import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.example.backend.nft.entity.nft.trait.NFTTraitDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NFTTraitMapperTest {

    @Test
    public void testToDto() {
        NFTTrait entity = NFTTrait.builder()
                .id(1L)
                .traitType("type")
                .displayType("display")
                .maxValue("max")
                .value("value")
                .build();

        NFTTraitDto dto = NFTTraitMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getTraitType(), dto.getTraitType());
        assertEquals(entity.getDisplayType(), dto.getDisplayType());
        assertEquals(entity.getMaxValue(), dto.getMaxValue());
        assertEquals(entity.getValue(), dto.getValue());
    }

    @Test
    public void testToEntity() {
        NFTTraitDto dto = NFTTraitDto.builder()
                .id(1L)
                .traitType("type")
                .displayType("display")
                .maxValue("max")
                .value("value")
                .build();

        NFTTrait entity = NFTTraitMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getTraitType(), entity.getTraitType());
        assertEquals(dto.getDisplayType(), entity.getDisplayType());
        assertEquals(dto.getMaxValue(), entity.getMaxValue());
        assertEquals(dto.getValue(), entity.getValue());
    }
}