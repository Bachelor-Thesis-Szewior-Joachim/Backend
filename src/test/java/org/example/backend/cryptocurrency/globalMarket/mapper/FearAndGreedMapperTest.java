package org.example.backend.cryptocurrency.globalMarket.mapper;

import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreed;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FearAndGreedMapperTest {

    @Test
    public void testToDto() {
        FearAndGreed entity = new FearAndGreed();
        entity.setValue(50);
        entity.setValueClassification("Neutral");
        entity.setDate("2023-10-01");

        FearAndGreedDto dto = FearAndGreedMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getValue(), dto.getValue());
        assertEquals(entity.getValueClassification(), dto.getValueClassification());
        assertEquals(entity.getDate(), dto.getDate());
    }

    @Test
    public void testToEntity() {
        FearAndGreedDto dto = new FearAndGreedDto(50, "Neutral", "2023-10-01");

        FearAndGreed entity = FearAndGreedMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getValue(), entity.getValue());
        assertEquals(dto.getValueClassification(), entity.getValueClassification());
        assertEquals(dto.getDate(), entity.getDate());
    }
}