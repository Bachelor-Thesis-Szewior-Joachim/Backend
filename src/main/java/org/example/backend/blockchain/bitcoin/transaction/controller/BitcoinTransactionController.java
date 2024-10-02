package org.example.backend.blockchain.bitcoin.transaction.controller;


import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.service.BitcoinTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bitcoin/transaction")
public class BitcoinTransactionController {

    private final BitcoinTransactionService bitcoinTransactionService;

    public BitcoinTransactionController(BitcoinTransactionService bitcoinTransactionService) {
        this.bitcoinTransactionService = bitcoinTransactionService;
    }

    @GetMapping("/data/{hash}")
    public String getTransaction(@PathVariable String hash) {
        return bitcoinTransactionService.getTransactionByHash(hash);
    }
}
