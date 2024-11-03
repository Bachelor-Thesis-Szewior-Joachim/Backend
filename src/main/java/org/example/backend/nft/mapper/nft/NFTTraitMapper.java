package org.example.backend.nft.mapper.nft;


import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.example.backend.nft.entity.nft.trait.NFTTraitDto;

public class NFTTraitMapper {

    public static NFTTrait toEntity(NFTTraitDto dto) {
        return NFTTrait.builder()
                .id(dto.getId())
                .traitType(dto.getTraitType())
                .displayType(dto.getDisplayType())
                .maxValue(dto.getMaxValue())
                .value(dto.getValue())
                .build();
    }

    public static NFTTraitDto toDto(NFTTrait entity) {
        return NFTTraitDto.builder()
                .id(entity.getId())
                .traitType(entity.getTraitType())
                .displayType(entity.getDisplayType())
                .maxValue(entity.getMaxValue())
                .value(entity.getValue())
                .build();
    }
}
