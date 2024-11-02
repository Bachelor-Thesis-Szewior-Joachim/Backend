package org.example.backend.nft.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.entity.collection.stats.CollectionStatsDto;

public class CollectionStatsMapper {

    public static CollectionStats toEntity(CollectionStatsDto dto) {
        return CollectionStats.builder()
                .collectionSlug(dto.getCollectionSlug())
                .floorPrice(dto.getFloorPrice())
                .totalVolume(dto.getTotalVolume())
                .numOwners(dto.getNumOwners())
                .totalSupply(dto.getTotalSupply())
                .build();
    }

    public static CollectionStatsDto toDto(CollectionStats entity) {
        return CollectionStatsDto.builder()
                .collectionSlug(entity.getCollectionSlug())
                .floorPrice(entity.getFloorPrice())
                .totalVolume(entity.getTotalVolume())
                .numOwners(entity.getNumOwners())
                .totalSupply(entity.getTotalSupply())
                .build();
    }

    public static CollectionStats mapJsonResponseToEntity(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode statsNode = rootNode.path("stats");

            return CollectionStats.builder()
                    .collectionSlug(rootNode.path("slug").asText())
                    .floorPrice(statsNode.path("floor_price").asDouble())
                    .totalVolume(statsNode.path("total_volume").asDouble())
                    .numOwners(statsNode.path("num_owners").asLong())
                    .totalSupply(statsNode.path("total_supply").asLong())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
