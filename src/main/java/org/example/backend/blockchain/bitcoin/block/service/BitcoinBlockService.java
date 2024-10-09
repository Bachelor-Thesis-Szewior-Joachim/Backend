package org.example.backend.blockchain.bitcoin.block.service;

import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlock;
import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlockDto;
import org.example.backend.blockchain.bitcoin.block.mapper.BitcoinBlockMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BitcoinBlockService {
    private final RestTemplate restTemplate;

    public BitcoinBlockService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BitcoinBlockDto getBitcoinBlockData(String blockHash) {
        String url = "https://api.blockcypher.com/v1/btc/main/blocks/" + blockHash;

        Optional<BitcoinBlock> bitcoinBlockOptional = Optional.ofNullable(restTemplate.getForObject(url, BitcoinBlock.class));

        if (bitcoinBlockOptional.isPresent()) {
            return BitcoinBlockMapper.mapBlockToBlockDto(bitcoinBlockOptional.get());
        } else {
            return null;
        }
    }
}
