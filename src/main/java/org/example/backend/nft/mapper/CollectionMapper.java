package org.example.backend.nft.mapper;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.CollectionDto;

import java.util.ArrayList;
import java.util.List;

public class CollectionMapper {

    public static CollectionDto toDto(Collection collection) {
        return CollectionDto.builder()
                .id(collection.getId())
                .slug(collection.getSlug())
                .name(collection.getName())
                .description(collection.getDescription())
                .imageUrl(collection.getImageUrl())
                .externalUrl(collection.getExternalUrl())
                .blockchain(collection.getBlockchain())
                .build();
    }

    public static Collection toEntity(CollectionDto collectionDto) {
        return Collection.builder()
                .id(collectionDto.getId())
                .slug(collectionDto.getSlug())
                .name(collectionDto.getName())
                .description(collectionDto.getDescription())
                .imageUrl(collectionDto.getImageUrl())
                .externalUrl(collectionDto.getExternalUrl())
                .blockchain(collectionDto.getBlockchain())
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
                        .slug(node.path("slug").asText())
                        .name(node.path("name").asText())
                        .description(node.path("description").asText())
                        .imageUrl(node.path("image_url").asText())
                        .externalUrl(node.path("external_url").asText())
                        .blockchain(blockchain)
                        .build();
                collections.add(collection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collections;
    }
}

