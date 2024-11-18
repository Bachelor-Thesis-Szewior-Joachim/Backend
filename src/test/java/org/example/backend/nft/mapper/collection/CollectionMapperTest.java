package org.example.backend.nft.mapper.collection;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.CollectionDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CollectionMapperTest {

    @Test
    public void testToDto() {
        Collection collection = Collection.builder()
                .id(1L)
                .slug("slug")
                .name("name")
                .description("description")
                .imageUrl("imageUrl")
                .bannerImageUrl("bannerImageUrl")
                .externalUrl("externalUrl")
                .blockchain("blockchain")
                .owner("owner")
                .category("category")
                .isDisabled(true)
                .traitOffersEnabled(true)
                .collectionOffersEnabled(true)
                .openseaUrl("openseaUrl")
                .projectUrl("projectUrl")
                .wikiUrl("wikiUrl")
                .discordUrl("discordUrl")
                .telegramUrl("telegramUrl")
                .twitterUsername("twitterUsername")
                .instagramUsername("instagramUsername")
                .contracts(List.of("contract1", "contract2"))
                .requiredZone("requiredZone")
                .totalSupply(100)
                .rarityStrategyVersion("v1")
                .createdDate("2023-10-01")
                .build();

        CollectionDto dto = CollectionMapper.toDto(collection);

        assertNotNull(dto);
        assertEquals(collection.getId(), dto.getId());
        assertEquals(collection.getSlug(), dto.getSlug());
        assertEquals(collection.getName(), dto.getName());
        assertEquals(collection.getDescription(), dto.getDescription());
        assertEquals(collection.getImageUrl(), dto.getImageUrl());
        assertEquals(collection.getBannerImageUrl(), dto.getBannerImageUrl());
        assertEquals(collection.getExternalUrl(), dto.getExternalUrl());
        assertEquals(collection.getBlockchain(), dto.getBlockchain());
        assertEquals(collection.getOwner(), dto.getOwner());
        assertEquals(collection.getCategory(), dto.getCategory());
        assertEquals(collection.isDisabled(), dto.isDisabled());
        assertEquals(collection.isTraitOffersEnabled(), dto.isTraitOffersEnabled());
        assertEquals(collection.isCollectionOffersEnabled(), dto.isCollectionOffersEnabled());
        assertEquals(collection.getOpenseaUrl(), dto.getOpenseaUrl());
        assertEquals(collection.getProjectUrl(), dto.getProjectUrl());
        assertEquals(collection.getWikiUrl(), dto.getWikiUrl());
        assertEquals(collection.getDiscordUrl(), dto.getDiscordUrl());
        assertEquals(collection.getTelegramUrl(), dto.getTelegramUrl());
        assertEquals(collection.getTwitterUsername(), dto.getTwitterUsername());
        assertEquals(collection.getInstagramUsername(), dto.getInstagramUsername());
        assertEquals(collection.getContracts(), dto.getContracts());
        assertEquals(collection.getRequiredZone(), dto.getRequiredZone());
        assertEquals(collection.getTotalSupply(), dto.getTotalSupply());
        assertEquals(collection.getRarityStrategyVersion(), dto.getRarityStrategyVersion());
        assertEquals(collection.getCreatedDate(), dto.getCreatedDate());
    }

    @Test
    public void testToEntity() {
        CollectionDto dto = CollectionDto.builder()
                .id(1L)
                .slug("slug")
                .name("name")
                .description("description")
                .imageUrl("imageUrl")
                .bannerImageUrl("bannerImageUrl")
                .externalUrl("externalUrl")
                .blockchain("blockchain")
                .owner("owner")
                .category("category")
                .isDisabled(true)
                .traitOffersEnabled(true)
                .collectionOffersEnabled(true)
                .openseaUrl("openseaUrl")
                .projectUrl("projectUrl")
                .wikiUrl("wikiUrl")
                .discordUrl("discordUrl")
                .telegramUrl("telegramUrl")
                .twitterUsername("twitterUsername")
                .instagramUsername("instagramUsername")
                .contracts(List.of("contract1", "contract2"))
                .requiredZone("requiredZone")
                .totalSupply(100)
                .rarityStrategyVersion("v1")
                .createdDate("2023-10-01")
                .build();

        Collection entity = CollectionMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getSlug(), entity.getSlug());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getImageUrl(), entity.getImageUrl());
        assertEquals(dto.getBannerImageUrl(), entity.getBannerImageUrl());
        assertEquals(dto.getExternalUrl(), entity.getExternalUrl());
        assertEquals(dto.getBlockchain(), entity.getBlockchain());
        assertEquals(dto.getOwner(), entity.getOwner());
        assertEquals(dto.getCategory(), entity.getCategory());
        assertEquals(dto.isDisabled(), entity.isDisabled());
        assertEquals(dto.isTraitOffersEnabled(), entity.isTraitOffersEnabled());
        assertEquals(dto.isCollectionOffersEnabled(), entity.isCollectionOffersEnabled());
        assertEquals(dto.getOpenseaUrl(), entity.getOpenseaUrl());
        assertEquals(dto.getProjectUrl(), entity.getProjectUrl());
        assertEquals(dto.getWikiUrl(), entity.getWikiUrl());
        assertEquals(dto.getDiscordUrl(), entity.getDiscordUrl());
        assertEquals(dto.getTelegramUrl(), entity.getTelegramUrl());
        assertEquals(dto.getTwitterUsername(), entity.getTwitterUsername());
        assertEquals(dto.getInstagramUsername(), entity.getInstagramUsername());
        assertEquals(dto.getContracts(), entity.getContracts());
        assertEquals(dto.getRequiredZone(), entity.getRequiredZone());
        assertEquals(dto.getTotalSupply(), entity.getTotalSupply());
        assertEquals(dto.getRarityStrategyVersion(), entity.getRarityStrategyVersion());
        assertEquals(dto.getCreatedDate(), entity.getCreatedDate());
    }

    @Test
    public void testMapJsonResponseToEntities() throws Exception {
        String jsonResponse = "{ \"collections\": [ { \"collection\": \"slug\", \"name\": \"name\", \"description\": \"description\", \"image_url\": \"imageUrl\", \"opensea_url\": \"externalUrl\", \"owner\": \"owner\" } ] }";

        List<Collection> collections = CollectionMapper.mapJsonResponseToEntities(jsonResponse, "blockchain");

        assertNotNull(collections);
        assertEquals(1, collections.size());

        Collection collection = collections.get(0);
        assertEquals("slug", collection.getSlug());
        assertEquals("name", collection.getName());
        assertEquals("description", collection.getDescription());
        assertEquals("imageUrl", collection.getImageUrl());
        assertEquals("externalUrl", collection.getExternalUrl());
        assertEquals("owner", collection.getOwner());
        assertEquals("blockchain", collection.getBlockchain());
    }

    @Test
    public void testMapJsonResponseToEntity() throws Exception {
        String jsonResponse = "{ \"name\": \"name\", \"description\": \"description\", \"image_url\": \"imageUrl\", \"banner_image_url\": \"bannerImageUrl\", \"external_url\": \"externalUrl\", \"owner\": \"owner\", \"category\": \"category\", \"is_disabled\": true, \"trait_offers_enabled\": true, \"collection_offers_enabled\": true, \"opensea_url\": \"openseaUrl\", \"project_url\": \"projectUrl\", \"wiki_url\": \"wikiUrl\", \"discord_url\": \"discordUrl\", \"telegram_url\": \"telegramUrl\", \"twitter_username\": \"twitterUsername\", \"instagram_username\": \"instagramUsername\", \"required_zone\": \"requiredZone\", \"total_supply\": 100, \"rarity\": { \"strategy_version\": \"v1\" }, \"created_date\": \"2023-10-01\", \"contracts\": [ { \"address\": \"contract1\" }, { \"address\": \"contract2\" } ] }";

        Collection existingCollection = Collection.builder().build();

        Collection collection = CollectionMapper.mapJsonResponseToEntity(jsonResponse, existingCollection);

        assertNotNull(collection);
        assertEquals("name", collection.getName());
        assertEquals("description", collection.getDescription());
        assertEquals("imageUrl", collection.getImageUrl());
        assertEquals("bannerImageUrl", collection.getBannerImageUrl());
        assertEquals("externalUrl", collection.getExternalUrl());
        assertEquals("owner", collection.getOwner());
        assertEquals("category", collection.getCategory());
        assertEquals(true, collection.isDisabled());
        assertEquals(true, collection.isTraitOffersEnabled());
        assertEquals(true, collection.isCollectionOffersEnabled());
        assertEquals("openseaUrl", collection.getOpenseaUrl());
        assertEquals("projectUrl", collection.getProjectUrl());
        assertEquals("wikiUrl", collection.getWikiUrl());
        assertEquals("discordUrl", collection.getDiscordUrl());
        assertEquals("telegramUrl", collection.getTelegramUrl());
        assertEquals("twitterUsername", collection.getTwitterUsername());
        assertEquals("instagramUsername", collection.getInstagramUsername());
        assertEquals("requiredZone", collection.getRequiredZone());
        assertEquals(100, collection.getTotalSupply());
        assertEquals("v1", collection.getRarityStrategyVersion());
        assertEquals("2023-10-01", collection.getCreatedDate());
        assertEquals(List.of("contract1", "contract2"), collection.getContracts());
    }
}