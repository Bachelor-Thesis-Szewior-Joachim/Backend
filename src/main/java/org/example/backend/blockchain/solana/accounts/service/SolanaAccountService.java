package org.example.backend.blockchain.solana.accounts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SolanaAccountService {


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SolanaAccountService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String SOLANA_RPC_URL = "https://api.devnet.solana.com";


    public Optional<String> getAccountInfo(String address) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getBlockProduction");
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

        //return Optional.empty();
    }

    public Optional<String> getAccountBalance(String address) {
        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getBalance");
            requestBody.put("params", new Object[]{
                    address
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

    public Optional<String> getSolanaBiggestAccounts() {
        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getLargestAccounts");
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

    public Optional<String> getProgramAccounts(String address) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getProgramAccounts");
            requestBody.put("params", new Object[]{
                    address
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
        }    }
}
