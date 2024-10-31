package org.example.backend.cryptocurrency.globalMarket.service;
import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;
import org.example.backend.cryptocurrency.globalMarket.mapper.FearAndGreedMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FearAndGreedService {

    private final RestTemplate restTemplate;

    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com";

    public FearAndGreedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<FearAndGreedDto> getLatestFearAndGreed() {
        try {
            // API URL for historical fear & greed data
            String url = API_URL + "/v3/fearandgreed/historical?CMC_PRO_API_KEY=" + API_KEY;
            String response = restTemplate.getForObject(url, String.class);

            // Parse the response into a FearAndGreed entity or DTO (you might use ObjectMapper here)
            FearAndGreedDto dto = parseJsonToDto(response);

            return Optional.of(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private FearAndGreedDto parseJsonToDto(String jsonResponse) {

        return new FearAndGreedDto(50, "Neutral", LocalDate.now());
    }
}
