package org.example.backend.nft.mapper.collection;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.CollectionDto;

import java.util.ArrayList;
import java.util.List;

public class CollectionMapper {

    public static CollectionDto toDto(Collection collection) {
        if (collection == null) {
            return null;
        }

        return CollectionDto.builder()
                .id(collection.getId())
                .slug(collection.getSlug())
                .name(collection.getName())
                .description(collection.getDescription())
                .imageUrl(collection.getImageUrl())
                .bannerImageUrl(collection.getBannerImageUrl())
                .externalUrl(collection.getExternalUrl())
                .blockchain(collection.getBlockchain())
                .owner(collection.getOwner())
                .category(collection.getCategory())
                .isDisabled(collection.isDisabled())
                .traitOffersEnabled(collection.isTraitOffersEnabled())
                .collectionOffersEnabled(collection.isCollectionOffersEnabled())
                .openseaUrl(collection.getOpenseaUrl())
                .projectUrl(collection.getProjectUrl())
                .wikiUrl(collection.getWikiUrl())
                .discordUrl(collection.getDiscordUrl())
                .telegramUrl(collection.getTelegramUrl())
                .twitterUsername(collection.getTwitterUsername())
                .instagramUsername(collection.getInstagramUsername())
                .contracts(collection.getContracts())
                .requiredZone(collection.getRequiredZone())
                .totalSupply(collection.getTotalSupply())
                .rarityStrategyVersion(collection.getRarityStrategyVersion())
                .createdDate(collection.getCreatedDate())
                .build();
    }

    public static Collection toEntity(CollectionDto collectionDto) {
        if (collectionDto == null) {
            return null;
        }

        return Collection.builder()
                .id(collectionDto.getId())
                .slug(collectionDto.getSlug())
                .name(collectionDto.getName())
                .description(collectionDto.getDescription())
                .imageUrl(collectionDto.getImageUrl())
                .bannerImageUrl(collectionDto.getBannerImageUrl())
                .externalUrl(collectionDto.getExternalUrl())
                .blockchain(collectionDto.getBlockchain())
                .owner(collectionDto.getOwner())
                .category(collectionDto.getCategory())
                .isDisabled(collectionDto.isDisabled())
                .traitOffersEnabled(collectionDto.isTraitOffersEnabled())
                .collectionOffersEnabled(collectionDto.isCollectionOffersEnabled())
                .openseaUrl(collectionDto.getOpenseaUrl())
                .projectUrl(collectionDto.getProjectUrl())
                .wikiUrl(collectionDto.getWikiUrl())
                .discordUrl(collectionDto.getDiscordUrl())
                .telegramUrl(collectionDto.getTelegramUrl())
                .twitterUsername(collectionDto.getTwitterUsername())
                .instagramUsername(collectionDto.getInstagramUsername())
                .contracts(collectionDto.getContracts())
                .requiredZone(collectionDto.getRequiredZone())
                .totalSupply(collectionDto.getTotalSupply())
                .rarityStrategyVersion(collectionDto.getRarityStrategyVersion())
                .createdDate(collectionDto.getCreatedDate())
                .build();
    }

    public static List<Collection> mapJsonResponseToEntities(String jsonResponse, String blockchain) {
        List<Collection> collections = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode collectionsNode = rootNode.path("collections");

            for (JsonNode node : collectionsNode) {
                Collection collection = Collection.builder()
                        .slug(node.path("collection").asText())
                        .name(node.path("name").asText())
                        .description(node.path("description").asText())
                        .imageUrl(node.path("image_url").asText())
                        .externalUrl(node.path("opensea_url").asText())
                        .owner(node.path("owner").asText())
                        .blockchain(blockchain)
                        .build();
                collections.add(collection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collections;
    }

    public static Collection mapJsonResponseToEntity(String jsonResponse, Collection existingCollection) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            existingCollection.setName(rootNode.path("name").asText());
            existingCollection.setDescription(rootNode.path("description").asText());
            existingCollection.setImageUrl(rootNode.path("image_url").asText());
            existingCollection.setBannerImageUrl(rootNode.path("banner_image_url").asText());
            existingCollection.setExternalUrl(rootNode.path("external_url").asText());
            existingCollection.setOwner(rootNode.path("owner").asText());
            existingCollection.setCategory(rootNode.path("category").asText());
            existingCollection.setDisabled(rootNode.path("is_disabled").asBoolean());
            existingCollection.setTraitOffersEnabled(rootNode.path("trait_offers_enabled").asBoolean());
            existingCollection.setCollectionOffersEnabled(rootNode.path("collection_offers_enabled").asBoolean());
            existingCollection.setOpenseaUrl(rootNode.path("opensea_url").asText());
            existingCollection.setProjectUrl(rootNode.path("project_url").asText());
            existingCollection.setWikiUrl(rootNode.path("wiki_url").asText());
            existingCollection.setDiscordUrl(rootNode.path("discord_url").asText());
            existingCollection.setTelegramUrl(rootNode.path("telegram_url").asText());
            existingCollection.setTwitterUsername(rootNode.path("twitter_username").asText());
            existingCollection.setInstagramUsername(rootNode.path("instagram_username").asText());
            existingCollection.setRequiredZone(rootNode.path("required_zone").asText());
            existingCollection.setTotalSupply(rootNode.path("total_supply").asInt());
            existingCollection.setRarityStrategyVersion(rootNode.path("rarity").path("strategy_version").asText());
            existingCollection.setCreatedDate(rootNode.path("created_date").asText());

            List<String> contracts = new ArrayList<>();
            rootNode.path("contracts").forEach(contractNode -> contracts.add(contractNode.path("address").asText()));
            existingCollection.setContracts(contracts);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return existingCollection;
    }

}

