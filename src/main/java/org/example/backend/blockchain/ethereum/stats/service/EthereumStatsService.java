package org.example.backend.blockchain.ethereum.stats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EthereumStatsService {


    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumStatsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Returns the estimated gas needed for a transaction based on the specified gas price.
    public String getGasEstimate(long gasPrice) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "gastracker")
                .queryParam("action", "gasestimate")
                .queryParam("gasprice", gasPrice)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Returns the current gas price information, including safe, average, and fast gas prices in Gwei.
    public String getGasOracle() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "gastracker")
                .queryParam("action", "gasoracle")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Returns the total circulating supply of Ether (ETH).
    public String getEthSupply() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethsupply")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Returns the Ethereum blockchain size between the specified start and end dates, sorted by ascending order.
    public String getChainSize(String startDate, String endDate, String clientType, String syncMode) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "chainsize")
                .queryParam("startdate", startDate)
                .queryParam("enddate", endDate)
                .queryParam("clienttype", clientType)  // For example, "geth" for Go Ethereum.
                .queryParam("syncmode", syncMode)      // For example, "default" sync mode.
                .queryParam("sort", "asc")             // Sorting results in ascending order.
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Returns the total number of active nodes on the Ethereum network.
    public String getNodeCount() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "nodecount")
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
