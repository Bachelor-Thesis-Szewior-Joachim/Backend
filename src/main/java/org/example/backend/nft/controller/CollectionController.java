package org.example.backend.nft.controller;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.CollectionDto;
import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.entity.collection.stats.CollectionStatsDto;
import org.example.backend.nft.mapper.CollectionMapper;
import org.example.backend.nft.mapper.CollectionStatsMapper;
import org.example.backend.nft.service.CollectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{slug}/stats")
    public CollectionStatsDto getCollectionStats(@PathVariable String slug) {
        CollectionStats collectionStats = collectionService.fetchAndSaveCollectionStats(slug);
        return CollectionStatsMapper.toDto(collectionStats);
    }

    @GetMapping("/fetch-collections")
    public String fetchCollections() {
        System.out.println("Fetching collections");
        collectionService.fetchAndSaveCollectionsForBlockchains(Arrays.asList("solana", "ethereum", "klaytn"));
        return "Collections fetched and saved successfully!";
    }
}
