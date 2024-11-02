package org.example.backend.nft.entity.collection.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionStatsDto {
    private String collectionSlug;
    private Double floorPrice;
    private Double totalVolume;
    private Long numOwners;
    private Long totalSupply;
}

