package org.example.backend.blockchain.data.bitcoin;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinService {

    private final RestTemplate restTemplate;

    public BitcoinService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getBitcoinBlockData(String blockHash) {
        String url = "https://api.blockcypher.com/v1/btc/main/blocks/" + blockHash;
        return restTemplate.getForObject(url, String.class);
    }

    public String getAllAccountData(String address) {
        String url = "https://api.blockcypher.com/v1/btc/main/addrs/" + address + "/full";
        return restTemplate.getForObject(url, String.class);
    }

    public String getTransactionByHash(String hash) {
        String url = "https://api.blockcypher.com/v1/btc/main/txs/" + hash;
        return restTemplate.getForObject(url, String.class);
    }
}
