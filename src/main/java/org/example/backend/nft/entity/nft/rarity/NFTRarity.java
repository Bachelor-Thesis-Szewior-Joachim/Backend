package org.example.backend.nft.entity.nft.rarity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NFTRarity {
    private String strategyVersion;
    private int rank;
    private double score;
    private String calculatedAt;
    private int maxRank;
    private int totalSupply;
    private int uniqueAttributeCount;
}
