package org.example.backend.nft.service;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.mapper.CollectionMapper;
import org.example.backend.nft.mapper.CollectionStatsMapper;
import org.example.backend.nft.repository.CollectionRepository;
import org.example.backend.nft.repository.CollectionStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CollectionService {

    private static final String OPENSEA_API_URL = "https://api.opensea.io/api/v1/collection";
    private final RestTemplate restTemplate;
    private CollectionStatsRepository collectionStatsRepository;
    private final CollectionRepository collectionRepository;

    public CollectionService(RestTemplate restTemplate, CollectionRepository collectionRepository) {
        this.restTemplate = restTemplate;
        this.collectionRepository = collectionRepository;
    }

    public CollectionStats fetchAndSaveCollectionStats(String collectionSlug) {
        String response = restTemplate.getForObject(OPENSEA_API_URL + "/" + collectionSlug + "/stats", String.class);
        CollectionStats collectionStats = CollectionStatsMapper.mapJsonResponseToEntity(response);
        return collectionStatsRepository.save(collectionStats);
    }

    public void fetchAndSaveCollectionsForBlockchains(List<String> blockchains) {
        for (String blockchain : blockchains) {
            for (int offset = 0; offset < 20; offset += 20) { // Iterate with offset to get up to 20 items
                String url = OPENSEA_API_URL + "?offset=" + offset + "&limit=20&blockchain=" + blockchain;
                String response = restTemplate.getForObject(url, String.class);

                List<Collection> collections = CollectionMapper.mapJsonResponseToEntities(response, blockchain);
                collectionRepository.saveAll(collections);
            }
        }
    }
}
