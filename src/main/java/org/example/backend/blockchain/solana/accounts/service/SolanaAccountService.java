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

    public Optional<SolanaAccountDto> getAccountInfo(String address) {

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //        try {
//            // Build the request JSON manually to avoid string formatting issues
//            ObjectNode requestBody = objectMapper.createObjectNode();
//            requestBody.put("jsonrpc", "2.0");
//            requestBody.put("id", 1);
//            requestBody.put("method", "getAccountInfo");
//
//            // Create the "params" array
//            ObjectNode encodingNode = objectMapper.createObjectNode();
//            encodingNode.put("encoding", "base58");
//
//            ArrayNode params = objectMapper.createArrayNode();
//            params.add(address);
//            params.add(encodingNode);
//
//            requestBody.set("params", params);
//
//            // Set headers for the request
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON); // Setting content-type to application/json
//
//            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers); // Use .toString() to send raw JSON
//
//            // Send the API request and get the response
//            ResponseEntity<String> response = restTemplate.exchange(SOLANA_RPC_URL, HttpMethod.POST, entity, String.class);
//            String responseBody = response.getBody();
//
//            System.out.println("Response Body: " + responseBody); // Debugging response
//
//            if (responseBody != null) {
//                // Parse response as JsonNode
//                JsonNode jsonNode = objectMapper.readTree(responseBody);
//                JsonNode accountInfoNode = jsonNode.get("result").get("value"); // Fetch "value" node
//                JsonNode slotNode = jsonNode.get("context").get("slot"); // Fetch "context.slot"
//
//                // Check if accountInfoNode exists
//                if (accountInfoNode != null && !accountInfoNode.isNull()) {
//                    // Map to SolanaAccount and SolanaAccountDto
//                    SolanaAccount solanaAccount = SolanaAccountMapper.mapToSolanaAccount(accountInfoNode, slotNode.asLong());
//                    SolanaAccountDto solanaAccountDto = SolanaAccountMapper.mapToDto(solanaAccount);
//                    return Optional.of(solanaAccountDto);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error parsing Solana account data: " + e);
//        }

        return Optional.empty();
    }

}
