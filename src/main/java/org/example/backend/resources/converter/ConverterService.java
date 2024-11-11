package org.example.backend.resources.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConverterService {

    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com/v2/tools/price-conversion";
    private final RestTemplate restTemplate;
    private ObjectMapper objectMapper = new ObjectMapper();
    public ConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String convert(String baseCurrency, String targetCurrency, double amount) {
        String url = API_URL + "?amount=" + amount + "&id=" + baseCurrency + "&convert=" + targetCurrency;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            return rootNode.path("data").path("quote").path(targetCurrency).get("price").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
