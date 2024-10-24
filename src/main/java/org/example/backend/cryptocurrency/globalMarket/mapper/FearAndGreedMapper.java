package org.example.backend.cryptocurrency.globalMarket.mapper;

import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreed;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;
import org.springframework.stereotype.Component;


public class FearAndGreedMapper {

    public static FearAndGreedDto toDto(FearAndGreed entity) {
        return new FearAndGreedDto(
                entity.getValue(),
                entity.getValueClassification(),
                entity.getTimestamp()
        );
    }

    public static FearAndGreed toEntity(FearAndGreedDto dto) {
        FearAndGreed entity = new FearAndGreed();
        entity.setValue(dto.getValue());
        entity.setValueClassification(dto.getValueClassification());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }
}
