package org.example.backend.blockchain.bitcoin.accounts.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinAccountService {
    private final RestTemplate restTemplate;

    public BitcoinAccountService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllAccountData(String address) {
        String url = "https://api.blockcypher.com/v1/btc/main/addrs/" + address + "/full";
        return restTemplate.getForObject(url, String.class);
    }
}
