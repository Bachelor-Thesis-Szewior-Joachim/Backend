package org.example.backend.blockchain.bitcoin.transaction.controller;


import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransactionDto;
import org.example.backend.blockchain.bitcoin.transaction.service.BitcoinTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bitcoin/transaction")
public class BitcoinTransactionController {

    private final BitcoinTransactionService bitcoinTransactionService;

    public BitcoinTransactionController(BitcoinTransactionService bitcoinTransactionService) {
        this.bitcoinTransactionService = bitcoinTransactionService;
    }

    @GetMapping("/data/{hash}")
    public ResponseEntity<BitcoinTransactionDto> getTransaction(@PathVariable String hash) {

        Optional<BitcoinTransactionDto> bitcoinTransactionDtoOptional =
                Optional.ofNullable(bitcoinTransactionService.getTransactionByHash(hash));

        if (bitcoinTransactionDtoOptional.isPresent()) {
            return ResponseEntity.ok(bitcoinTransactionDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
