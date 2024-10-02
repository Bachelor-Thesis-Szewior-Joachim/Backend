package org.example.backend.blockchain.bitcoin.transaction.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinTransactionService {

    private final RestTemplate restTemplate;

    public BitcoinTransactionService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getTransactionByHash(String hash) {
        String url = "https://api.blockcypher.com/v1/btc/main/txs/" + hash;
        return restTemplate.getForObject(url, String.class);
    }
}
