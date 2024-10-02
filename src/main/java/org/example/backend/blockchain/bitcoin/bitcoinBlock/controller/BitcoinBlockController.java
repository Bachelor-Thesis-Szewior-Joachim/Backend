package org.example.backend.blockchain.bitcoin.bitcoinBlock.controller;

import org.example.backend.blockchain.bitcoin.bitcoinBlock.service.BitcoinBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bitcoin/block")
public class BitcoinBlockController {

    private final BitcoinBlockService bitcoinBlockService;

    public BitcoinBlockController(BitcoinBlockService bitcoinBlockService) {
        this.bitcoinBlockService = bitcoinBlockService;
    }

    @GetMapping("/data/{blockHash}")
    public String getBlockData(@PathVariable String blockHash) {
        return bitcoinBlockService.getBitcoinBlockData(blockHash);
    }
}
