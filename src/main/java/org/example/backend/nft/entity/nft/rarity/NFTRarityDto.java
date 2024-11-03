package org.example.backend.nft.entity.nft.rarity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NFTRarityDto {
    private String strategyVersion;
    private int rank;
    private double score;
    private String calculatedAt;
    private int maxRank;
    private int totalSupply;
    private int uniqueAttributeCount;
}
