package org.example.backend.blockchain.bitcoin.bitcoinBlock.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinBlockService {
    private final RestTemplate restTemplate;

    public BitcoinBlockService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBitcoinBlockData(String blockHash) {
        String url = "https://api.blockcypher.com/v1/btc/main/blocks/" + blockHash;
        return restTemplate.getForObject(url, String.class);
    }
}
