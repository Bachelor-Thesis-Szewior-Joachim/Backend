package org.example.backend.nft.mapper.nft;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.nft.NFT;
import org.example.backend.nft.entity.nft.NFTDto;
import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.rarity.NFTRarity;
import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NFTMapperTest {

    @Test
    public void testToDto() {
        NFT nft = NFT.builder()
                .id(1L)
                .identifier("identifier")
                .contract("contract")
                .tokenStandard("ERC-721")
                .name("name")
                .description("description")
                .imageUrl("imageUrl")
                .displayImageUrl("displayImageUrl")
                .displayAnimationUrl("displayAnimationUrl")
                .metadataUrl("metadataUrl")
                .openseaUrl("openseaUrl")
                .updatedAt("2023-10-01")
                .animationUrl("animationUrl")
                .creator("creator")
                .build();

        NFTDto dto = NFTMapper.toDto(nft);

        assertNotNull(dto);
        assertEquals(nft.getId(), dto.getId());
        assertEquals(nft.getIdentifier(), dto.getIdentifier());
        assertEquals(nft.getContract(), dto.getContract());
        assertEquals(nft.getTokenStandard(), dto.getTokenStandard());
        assertEquals(nft.getName(), dto.getName());
        assertEquals(nft.getDescription(), dto.getDescription());
        assertEquals(nft.getImageUrl(), dto.getImageUrl());
        assertEquals(nft.getDisplayImageUrl(), dto.getDisplayImageUrl());
        assertEquals(nft.getDisplayAnimationUrl(), dto.getDisplayAnimationUrl());
        assertEquals(nft.getMetadataUrl(), dto.getMetadataUrl());
        assertEquals(nft.getOpenseaUrl(), dto.getOpenseaUrl());
        assertEquals(nft.getUpdatedAt(), dto.getUpdatedAt());
        assertEquals(nft.getAnimationUrl(), dto.getAnimationUrl());
        assertEquals(nft.getCreator(), dto.getCreator());
    }

    @Test
    public void testToEntity() {
        NFTDto dto = new NFTDto();
        dto.setId(1L);
        dto.setIdentifier("identifier");
        dto.setContract("contract");
        dto.setTokenStandard("ERC-721");
        dto.setName("name");
        dto.setDescription("description");
        dto.setImageUrl("imageUrl");
        dto.setDisplayImageUrl("displayImageUrl");
        dto.setDisplayAnimationUrl("displayAnimationUrl");
        dto.setMetadataUrl("metadataUrl");
        dto.setOpenseaUrl("openseaUrl");
        dto.setUpdatedAt("2023-10-01");
        dto.setAnimationUrl("animationUrl");
        dto.setCreator("creator");

        NFT entity = NFTMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getIdentifier(), entity.getIdentifier());
        assertEquals(dto.getContract(), entity.getContract());
        assertEquals(dto.getTokenStandard(), entity.getTokenStandard());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getImageUrl(), entity.getImageUrl());
        assertEquals(dto.getDisplayImageUrl(), entity.getDisplayImageUrl());
        assertEquals(dto.getDisplayAnimationUrl(), entity.getDisplayAnimationUrl());
        assertEquals(dto.getMetadataUrl(), entity.getMetadataUrl());
        assertEquals(dto.getOpenseaUrl(), entity.getOpenseaUrl());
        assertEquals(dto.getUpdatedAt(), entity.getUpdatedAt());
        assertEquals(dto.getAnimationUrl(), entity.getAnimationUrl());
        assertEquals(dto.getCreator(), entity.getCreator());
    }

    @Test
    public void testMapJsonResponseToEntities() throws Exception {
        String jsonResponse = "{ \"nfts\": [ { \"identifier\": \"identifier\", \"contract\": \"contract\", \"name\": \"name\", \"description\": \"description\", \"image_url\": \"imageUrl\", \"opensea_url\": \"openseaUrl\", \"updated_at\": \"2023-10-01\", \"is_disabled\": true, \"is_nsfw\": false, \"is_suspicious\": false, \"traits\": [ { \"trait_type\": \"type\", \"display_type\": \"display\", \"max_value\": \"max\", \"value\": \"value\" } ], \"owners\": [ { \"address\": \"address\", \"quantity\": 1 } ], \"rarity\": { \"strategy_version\": \"v1\", \"rank\": 1, \"score\": 100.0, \"calculated_at\": \"2023-10-01\", \"max_rank\": 10, \"total_supply\": 100, \"ranking_features\": { \"unique_attribute_count\": 5 } } } ] }";

        Collection collection = Collection.builder().id(1L).build();

        List<NFT> nfts = NFTMapper.mapJsonResponseToEntities(jsonResponse, collection);

        assertNotNull(nfts);
        assertEquals(1, nfts.size());

        NFT nft = nfts.get(0);
        assertEquals("identifier", nft.getIdentifier());
        assertEquals(collection, nft.getCollection());
        assertEquals("contract", nft.getContract());
        assertEquals("name", nft.getName());
        assertEquals("description", nft.getDescription());
        assertEquals("imageUrl", nft.getImageUrl());
        assertEquals("openseaUrl", nft.getOpenseaUrl());
        assertEquals("2023-10-01", nft.getUpdatedAt());
        assertEquals(true, nft.getIsDisabled());
        assertEquals(false, nft.getIsNsfw());
        assertEquals(false, nft.getIsSuspicious());

        List<NFTTrait> traits = nft.getTraits();
        assertNotNull(traits);
        assertEquals(1, traits.size());
        NFTTrait trait = traits.get(0);
        assertEquals("type", trait.getTraitType());
        assertEquals("display", trait.getDisplayType());
        assertEquals("max", trait.getMaxValue());
        assertEquals("value", trait.getValue());

        List<NFTOwner> owners = nft.getOwners();
        assertNotNull(owners);
        assertEquals(1, owners.size());
        NFTOwner owner = owners.get(0);
        assertEquals("address", owner.getAddress());
        assertEquals(1, owner.getQuantity());

        NFTRarity rarity = nft.getRarity();
        assertNotNull(rarity);
        assertEquals("v1", rarity.getStrategyVersion());
        assertEquals(1, rarity.getRank());
        assertEquals(100.0, rarity.getScore());
        assertEquals("2023-10-01", rarity.getCalculatedAt());
        assertEquals(10, rarity.getMaxRank());
        assertEquals(100, rarity.getTotalSupply());
        assertEquals(5, rarity.getUniqueAttributeCount());
    }
}