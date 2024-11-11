package org.example.backend.blockchain.solana.network.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.block.mapper.SolanaSimpleJsonMapper;
import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.example.backend.blockchain.solana.network.mapper.SolanaEpochMapper;
import org.example.backend.blockchain.solana.network.mapper.SolanaSupplyMapper;
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

    public Optional<SupplyDto> getSupply() {
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
            SupplyDto resultSupplyDto = SolanaSupplyMapper.mapFromJsonToSupplyDto(response.getBody());

            return Optional.ofNullable(resultSupplyDto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<EpochDto> getCurrentEpochInfo() {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getEpochInfo");
            requestBody.put("params", new Object[]{});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return Optional.ofNullable(SolanaEpochMapper.mapJsonToEpochDto(response.getBody()));
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
            });

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return Optional.ofNullable(SolanaSimpleJsonMapper.mapJsonToResult(response.getBody()));
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
            });

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return Optional.ofNullable(SolanaSimpleJsonMapper.mapJsonToResult(response.getBody()));
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

            return Optional.ofNullable(SolanaSimpleJsonMapper.mapJsonToResult(response.getBody()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
