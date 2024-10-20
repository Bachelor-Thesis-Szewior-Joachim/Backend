package org.example.backend.blockchain.solana.node.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.node.entity.SolanaClusterNode;
import org.example.backend.blockchain.solana.node.entity.SolanaClusterNodeDto;
import org.example.backend.blockchain.solana.node.mapper.SolanaNodeMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NodeService {

    private final RestTemplate restTemplate;

    public NodeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Optional<List<SolanaClusterNodeDto>> getClusterNodes() {

        try {
            String url = "https://solana-mainnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "getClusterNodes");
            requestBody.put("params", new Object[]{});  // Use empty array for default params

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return Optional.ofNullable(SolanaNodeMapper.mapJsonResponseToSolanaClusterNodeDto(response.getBody()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }
}
