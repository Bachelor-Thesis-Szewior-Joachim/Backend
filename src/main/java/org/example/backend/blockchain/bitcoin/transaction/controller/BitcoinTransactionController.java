package org.example.backend.blockchain.bitcoin.transaction.controller;


import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class BitcoinTransactionController {

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody BitcoinTransaction bitcoinTransaction) {

        return new ResponseEntity<>(new Error("Couldn't create transaction"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getByHash")
    public ResponseEntity<?> getTransactionByHash(@RequestParam String hash) {

        return new ResponseEntity<>(new Error("Couldn't find transaction"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listOfTransactions")
    public ResponseEntity<?> getTransactionList(@RequestParam Integer limit) {

        return new ResponseEntity<>(new Error("Couldn't load transactions"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/estimatedCost")
    public ResponseEntity<?> getEstimatedCost() {

        return new ResponseEntity<>(new Error("Couldn't get estimated cost of the transactions"), HttpStatus.NOT_FOUND);
    }
}
