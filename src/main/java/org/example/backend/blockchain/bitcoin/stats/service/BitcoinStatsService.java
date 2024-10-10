package org.example.backend.blockchain.bitcoin.stats.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public class BitcoinStatsService {

    private final RestTemplate restTemplate;

    public BitcoinStatsService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBitcoinMainStatus() {
        String url = "https://api.blockcypher.com/v1/btc/main";

        return restTemplate.getForObject(url, String.class);
    }
}
