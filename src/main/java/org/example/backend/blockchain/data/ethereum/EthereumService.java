package org.example.backend.blockchain.data.ethereum;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class EthereumService {

    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumService() {
        this.restTemplate = new RestTemplate();
    }

    public String getEtherBalance(String address) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "balance")
                .queryParam("address", address)
                .queryParam("tag", "latest")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Get normal transactions for an Ethereum address
    public String getNormalTransactions(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "txlist")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Get internal transactions for an Ethereum address
    public String getInternalTransactions(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "txlistinternal")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

}
