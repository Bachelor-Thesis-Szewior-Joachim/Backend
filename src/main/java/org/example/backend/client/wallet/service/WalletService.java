package org.example.backend.client.wallet.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletService {

    @Value("${bitcoin.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WalletService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Returns details of a specific wallet.
    public String getWalletDetails(String walletName) {
        String url = "https://api.blockcypher.com/v1/btc/main/wallets/" + walletName+ "?token=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // Returns details of a specific HD wallet.
    public String getHDWalletDetails(String walletName) {
        String url = "https://api.blockcypher.com/v1/btc/main/wallets/hd/" + walletName+ "?token=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // Returns details of a specific chain in an HD wallet.
    public String getHDChainDetails(String walletName, String chainIndex) {
        String url = "https://api.blockcypher.com/v1/btc/main/wallets/hd/" + walletName + "/chains/" + chainIndex+ "?token=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // Returns addresses associated with a specific HD wallet chain.
    public String getHDAddressDetails(String walletName, String chainIndex) {
        String url = "https://api.blockcypher.com/v1/btc/main/wallets/hd/" + walletName + "/chains/" + chainIndex + "/addrs"+ "?token=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

}
