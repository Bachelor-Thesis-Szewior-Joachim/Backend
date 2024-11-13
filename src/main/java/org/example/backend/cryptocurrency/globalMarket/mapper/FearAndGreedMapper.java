package org.example.backend.cryptocurrency.globalMarket.mapper;

import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreed;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;


public class FearAndGreedMapper {

    public static FearAndGreedDto toDto(FearAndGreed entity) {
        return new FearAndGreedDto(
                entity.getValue(),
                entity.getValueClassification(),
                entity.getDate()
        );
    }

    public static FearAndGreed toEntity(FearAndGreedDto dto) {
        FearAndGreed entity = new FearAndGreed();
        entity.setValue(dto.getValue());
        entity.setValueClassification(dto.getValueClassification());
        entity.setDate(dto.getDate());
        return entity;
    }
}
