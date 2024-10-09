package org.example.backend.blockchain.bitcoin.transaction.service;

import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransactionDto;
import org.example.backend.blockchain.bitcoin.transaction.mapper.BitcoinTransactionMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BitcoinTransactionService {

    private final RestTemplate restTemplate;

    public BitcoinTransactionService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BitcoinTransactionDto getTransactionByHash(String hash) {

        String url = "https://api.blockcypher.com/v1/btc/main/txs/" + hash;

        try {
            Optional<BitcoinTransaction> optionalBitcoinTransaction = Optional.ofNullable(restTemplate.getForObject(url, BitcoinTransaction.class));
            if (optionalBitcoinTransaction.isPresent()) {
                return BitcoinTransactionMapper.mapTransactionToTransactionDto(optionalBitcoinTransaction.get());
            } else {
                return null;
            }
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }
}
