package org.example.backend.blockchain.ethereum.block.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EthereumBlockService {

    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumBlockService() {
        this.restTemplate = new RestTemplate();
    }

    public String getBlockByNumber(long blockNumber) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "proxy")
                .queryParam("action", "eth_getBlockByNumber")
                .queryParam("tag", "0x" + Long.toHexString(blockNumber))  // Convert block number to hexadecimal format
                .queryParam("boolean", "true")  // true to include full transaction details
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    public String getMinedBlocks(String address, String blockType) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "getminedblocks")
                .queryParam("address", address)
                .queryParam("blocktype", blockType)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    public String getEthSupply() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethsupply")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    public String getEthPrice() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethprice")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
