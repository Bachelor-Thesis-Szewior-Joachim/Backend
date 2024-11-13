package org.example.backend.blockchain.solana.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.token.entity.splToken.SplToken;
import org.example.backend.blockchain.solana.token.entity.splToken.SplTokenDto;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccount;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccountDto;
import org.example.backend.blockchain.solana.token.mapper.SolanaTokenMapper;
import org.example.backend.blockchain.solana.token.mapper.SplTokenMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SolanaTokenService {

    private final RestTemplate restTemplate;

    public SolanaTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Optional<SolanaTokenAccountDto> getTokenAccountsByOwner(String address, String option, String pubkey) {
        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getTokenAccountsByOwner");

            // Create the filter map (either "mint" or "programId")
            Map<String, String> filter = new HashMap<>();
            if ("mint".equals(option)) {
                filter.put("mint", pubkey);
            } else if ("program".equals(option)) {
                filter.put("programId", pubkey);
            }
            Map<String, String> coding = new HashMap<>();
            coding.put("encoding", "jsonParsed");

            // Construct the params array with the address and the filter
            Object[] params = new Object[]{address, filter, coding};

            requestBody.put("params", params);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            SolanaTokenAccount solanaTokenAccount = SolanaTokenMapper.mapJsonToSolanaTokenAccount(response.getBody());
            return Optional.ofNullable(SolanaTokenMapper.toDto(solanaTokenAccount));
//            return Optional.ofNullable(response.getBody());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public Optional<SplTokenDto> getTokenAccountBalance(String address) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getTokenAccountBalance");
            requestBody.put("params", new Object[]{address});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            SplToken splToken = SplTokenMapper.mapJsonToSplToken(response.getBody());

            return Optional.ofNullable(SplTokenMapper.toDto(splToken));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }

    public Optional<SplTokenDto> getTokenSupply(String address) {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getTokenSupply");
            requestBody.put("params", new Object[]{address});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            SplToken splToken = SplTokenMapper.mapJsonToSplToken(response.getBody());

            return Optional.ofNullable(SplTokenMapper.toDto(splToken));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }
}
