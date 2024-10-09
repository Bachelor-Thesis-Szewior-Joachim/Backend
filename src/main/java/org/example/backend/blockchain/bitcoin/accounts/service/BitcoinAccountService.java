package org.example.backend.blockchain.bitcoin.accounts.service;

import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccount;
import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;
import org.example.backend.blockchain.bitcoin.accounts.mapper.BitcoinAccountMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BitcoinAccountService {
    private final RestTemplate restTemplate;

    public BitcoinAccountService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BitcoinAccountDto getAllAccountData(String address) {
        String url = "https://api.blockcypher.com/v1/btc/main/addrs/" + address + "/full";

        Optional<BitcoinAccount> bitcoinAccountOptional = Optional.ofNullable(restTemplate.getForObject(url, BitcoinAccount.class));

        if (bitcoinAccountOptional.isPresent()) {
            return BitcoinAccountMapper.mapAccountToAccountDto(bitcoinAccountOptional.get());
        } else {
            return null;
        }
    }
}
