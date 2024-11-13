package org.example.backend.nft.service;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.collection.CollectionDto;
import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.example.backend.nft.mapper.collection.CollectionMapper;
import org.example.backend.nft.repository.CollectionRepository;
import org.example.backend.nft.repository.CollectionStatsRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.example.backend.nft.mapper.collection.CollectionStatsMapper.mapJsonResponseToEntity;

@Service
@EnableScheduling
public class CollectionService {

    private static final String OPENSEA_API_URL = "https://api.opensea.io/api/v2/collections/";
    private final RestTemplate restTemplate;
    private CollectionStatsRepository collectionStatsRepository;
    private final CollectionRepository collectionRepository;

    public CollectionService(RestTemplate restTemplate, CollectionRepository collectionRepository) {
        this.restTemplate = restTemplate;
        this.collectionRepository = collectionRepository;
    }

    public CollectionStats fetchAndSaveCollectionStats(String collectionSlug) {
        String response = restTemplate.getForObject(OPENSEA_API_URL + "/" + collectionSlug + "/stats", String.class);
        CollectionStats collectionStats = mapJsonResponseToEntity(response);
        return collectionStatsRepository.save(collectionStats);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCollectionsFromOpensea() {
        List<Collection> collections = (List<Collection>) collectionRepository.findAll();

        for (Collection collection : collections) {
            try {
                String url = OPENSEA_API_URL + collection.getSlug();
                HttpHeaders headers = new HttpHeaders();
                headers.set("accept", "application/json");
                headers.set("x-api-key", "a48c02f835254acc9fdadfe443c16b26");

                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    Collection updatedCollection = CollectionMapper.mapJsonResponseToEntity(response.getBody(), collection);
                    collectionRepository.save(updatedCollection);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<CollectionDto> getCollection(String slug) {
        Optional<Collection> collection = Optional.of(collectionRepository.findBySlug(slug));

        return collection.map(CollectionMapper::toDto);
    }

    public Optional<List<CollectionDto>> getCollectionsByBlockchain(String blockchain) {
        Optional<List<Collection>> optionalCollections = Optional.of(collectionRepository.findByBlockchain(blockchain));

        if (!optionalCollections.get().isEmpty()) {
            List<CollectionDto> collectionDtos = optionalCollections
                    .get()
                    .stream()
                    .map(CollectionMapper::toDto)
                    .toList();
            return Optional.of(collectionDtos);
        } else {
            return Optional.empty();
        }
    }
}
