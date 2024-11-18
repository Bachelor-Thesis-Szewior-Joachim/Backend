package org.example.backend.nft.mapper.collection;

import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.entity.collection.stats.CollectionStatsDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CollectionStatsMapperTest {

    @Test
    public void testToDto() {
        CollectionStats entity = CollectionStats.builder()
                .collectionSlug("slug")
                .floorPrice(1.23)
                .totalVolume(456.78)
                .numOwners(100L)
                .totalSupply(200L)
                .build();

        CollectionStatsDto dto = CollectionStatsMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getCollectionSlug(), dto.getCollectionSlug());
        assertEquals(entity.getFloorPrice(), dto.getFloorPrice());
        assertEquals(entity.getTotalVolume(), dto.getTotalVolume());
        assertEquals(entity.getNumOwners(), dto.getNumOwners());
        assertEquals(entity.getTotalSupply(), dto.getTotalSupply());
    }

    @Test
    public void testToEntity() {
        CollectionStatsDto dto = CollectionStatsDto.builder()
                .collectionSlug("slug")
                .floorPrice(1.23)
                .totalVolume(456.78)
                .numOwners(100L)
                .totalSupply(200L)
                .build();

        CollectionStats entity = CollectionStatsMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getCollectionSlug(), entity.getCollectionSlug());
        assertEquals(dto.getFloorPrice(), entity.getFloorPrice());
        assertEquals(dto.getTotalVolume(), entity.getTotalVolume());
        assertEquals(dto.getNumOwners(), entity.getNumOwners());
        assertEquals(dto.getTotalSupply(), entity.getTotalSupply());
    }

    @Test
    public void testMapJsonResponseToEntity() throws Exception {
        String jsonResponse = "{ \"slug\": \"slug\", \"stats\": { \"floor_price\": 1.23, \"total_volume\": 456.78, \"num_owners\": 100, \"total_supply\": 200 } }";

        CollectionStats entity = CollectionStatsMapper.mapJsonResponseToEntity(jsonResponse);

        assertNotNull(entity);
        assertEquals("slug", entity.getCollectionSlug());
        assertEquals(1.23, entity.getFloorPrice());
        assertEquals(456.78, entity.getTotalVolume());
        assertEquals(100L, entity.getNumOwners());
        assertEquals(200L, entity.getTotalSupply());
    }
}