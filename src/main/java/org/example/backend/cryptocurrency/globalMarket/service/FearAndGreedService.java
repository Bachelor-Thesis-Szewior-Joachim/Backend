package org.example.backend.cryptocurrency.globalMarket.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreed;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;
import org.example.backend.cryptocurrency.globalMarket.mapper.FearAndGreedMapper;
import org.example.backend.cryptocurrency.globalMarket.repository.FearAndGreedRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FearAndGreedService {

    private final RestTemplate restTemplate;
    private final FearAndGreedRepository fearAndGreedRepository;
    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com";

    public FearAndGreedService(RestTemplate restTemplate, FearAndGreedRepository fearAndGreedRepository) {
        this.restTemplate = restTemplate;
        this.fearAndGreedRepository = fearAndGreedRepository;
    }

    public void fetchAndSaveFearAndGreedData() {

        fearAndGreedRepository.deleteAll();
        try {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(API_URL+"/v3/fear-and-greed/historical")
                    .queryParam("CMC_PRO_API_KEY", API_KEY);

            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode dataNode = rootNode.path("data");

            List<FearAndGreed> fearAndGreedList = new ArrayList<>();
            for (JsonNode node : dataNode) {
                FearAndGreed fearAndGreed = new FearAndGreed();
                fearAndGreed.setDate(node.path("timestamp").asText());
                fearAndGreed.setValue(node.path("value").asInt());
                fearAndGreed.setValueClassification(node.path("value_classification").asText());
                fearAndGreedList.add(fearAndGreed);
            }

            fearAndGreedRepository.saveAll(fearAndGreedList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<List<FearAndGreedDto>> getLatestFearAndGreed() {

        List<FearAndGreed> fearAndGreedList = (List<FearAndGreed>) fearAndGreedRepository.findAll();
        List<FearAndGreedDto> fearAndGreedDtosList = fearAndGreedList.stream()
                .map(FearAndGreedMapper::toDto)
                .toList();

        return Optional.of(fearAndGreedDtosList);
    }
}
