package org.example.backend.nft.mapper.nft;

import org.example.backend.nft.entity.nft.rarity.NFTRarity;
import org.example.backend.nft.entity.nft.rarity.NFTRarityDto;

public class NFTRarityMapper {

    public static NFTRarity toEntity(NFTRarityDto dto) {
        return NFTRarity.builder()
                .strategyVersion(dto.getStrategyVersion())
                .rank(dto.getRank())
                .score(dto.getScore())
                .calculatedAt(dto.getCalculatedAt())
                .maxRank(dto.getMaxRank())
                .totalSupply(dto.getTotalSupply())
                .uniqueAttributeCount(dto.getUniqueAttributeCount())
                .build();
    }

    public static NFTRarityDto toDto(NFTRarity entity) {
        return NFTRarityDto.builder()
                .strategyVersion(entity.getStrategyVersion())
                .rank(entity.getRank())
                .score(entity.getScore())
                .calculatedAt(entity.getCalculatedAt())
                .maxRank(entity.getMaxRank())
                .totalSupply(entity.getTotalSupply())
                .uniqueAttributeCount(entity.getUniqueAttributeCount())
                .build();
    }
}

