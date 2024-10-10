package org.example.backend.blockchain.ethereum.token.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public String getTokenSupply(String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "tokensupply")
                .queryParam("contractaddress", contractAddress)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

}
