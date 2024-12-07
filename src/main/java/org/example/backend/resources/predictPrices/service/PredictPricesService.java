package org.example.backend.resources.predictPrices.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PredictPricesService {

    private final RestTemplate restTemplate;

    public PredictPricesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getAllPredictions(String dateInput) {
        String url = "http://model_server:5000/predict";
        System.out.println("Date input java: " + dateInput);
        Map<String, String> requestPayload = Map.of("date", dateInput);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return response.getBody();
    }
}
