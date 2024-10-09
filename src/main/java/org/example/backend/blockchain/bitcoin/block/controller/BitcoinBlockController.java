package org.example.backend.blockchain.bitcoin.block.controller;

import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlockDto;
import org.example.backend.blockchain.bitcoin.block.service.BitcoinBlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bitcoin/block")
public class BitcoinBlockController {

    private final BitcoinBlockService bitcoinBlockService;

    public BitcoinBlockController(BitcoinBlockService bitcoinBlockService) {
        this.bitcoinBlockService = bitcoinBlockService;
    }

    @GetMapping("/data/{blockHash}")
    public ResponseEntity<BitcoinBlockDto> getBlockData(@PathVariable String blockHash) {

        Optional<BitcoinBlockDto> bitcoinBlockDtoOptional = Optional.ofNullable(bitcoinBlockService.getBitcoinBlockData(blockHash));

        if (bitcoinBlockDtoOptional.isPresent()) {
            return ResponseEntity.ok(bitcoinBlockDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
