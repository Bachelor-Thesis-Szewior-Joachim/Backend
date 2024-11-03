package org.example.backend.nft.controller;

import org.example.backend.nft.entity.collection.CollectionDto;
import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.entity.collection.stats.CollectionStatsDto;
import org.example.backend.nft.mapper.collection.CollectionStatsMapper;
import org.example.backend.nft.service.CollectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/collection/{slug}")
    public ResponseEntity<CollectionDto> getCollection(@PathVariable String slug) {
        Optional<CollectionDto> collectionOptionalDto = collectionService.getCollection(slug);

        return collectionOptionalDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{blockchain}")
    public ResponseEntity<List<CollectionDto>> getCollectionsByBlockchain(@PathVariable String blockchain) {
        Optional<List<CollectionDto>> optionalCollectionDtoList = collectionService.getCollectionsByBlockchain(blockchain);

        return optionalCollectionDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/collection/{slug}/stats")
    public CollectionStatsDto getCollectionStats(@PathVariable String slug) {
        CollectionStats collectionStats = collectionService.fetchAndSaveCollectionStats(slug);
        return CollectionStatsMapper.toDto(collectionStats);
    }

    @GetMapping("/fetch-collections")
    public String fetchCollections() {
        System.out.println("Fetching collections");
        collectionService.updateCollectionsFromOpensea();
        return "Collections fetched and saved successfully!";
    }
}
