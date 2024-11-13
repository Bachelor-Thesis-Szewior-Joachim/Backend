package org.example.backend.blockchain.ethereum.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
public class EthereumTokenService {


    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumTokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Returns the total supply of an ERC-20 token for a specific contract address.
    public Optional<String> getTokenSupply(String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "tokensupply")
                .queryParam("contractaddress", contractAddress)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String tokenSupply = jsonNode.path("result").asText();
            return Optional.ofNullable(tokenSupply);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
