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

    @Value("${coinmarketcap.api.url}")
    private String apiUrl;

    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    public FearAndGreedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<FearAndGreedDto> getLatestFearAndGreed() {
        try {
            // API URL for historical fear & greed data
            String url = apiUrl + "/v3/fearandgreed/historical?CMC_PRO_API_KEY=" + apiKey;
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
