package org.example.backend.blockchain.solana.network.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class NetworkService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NetworkService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<String> getSupply() {
        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getSupply");
            requestBody.put("params", new Object[]{});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getCurrentEpochInfo() {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getVersion");
            requestBody.put("params", new Object[]{});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getFeeForMessage(String message) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getFeeForMessage");
            requestBody.put("params", new Object[]{
                    message
            });  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getGenesisHash() {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getGenesisHash");
            requestBody.put("params", new Object[]{
            });  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getFirstAvailableBlock() {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getFirstAvailableBlock");
            requestBody.put("params", new Object[]{
            });  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getMinimumBalanceForRentExemption(Long size) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getMinimumBalanceForRentExemption");
            requestBody.put("params", new Object[]{size});

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Print response
            System.out.println(response.getBody());
            return Optional.ofNullable(response.getBody().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
