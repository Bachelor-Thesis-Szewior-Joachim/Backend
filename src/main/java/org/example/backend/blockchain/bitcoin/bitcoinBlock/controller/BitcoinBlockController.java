package org.example.backend.blockchain.bitcoin.bitcoinBlock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block")
public class BitcoinBlockController {

    @GetMapping("/{hash}")
    public ResponseEntity<?> getBlock(@PathVariable String hash) {

        return new ResponseEntity<>(new Error("Couldn't fine the block with this hash: "+hash), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listOfBlocks")
    public ResponseEntity<?> getBlocks(@RequestParam String name, @RequestParam Integer limit) {

        return new ResponseEntity<>(new Error("Couldn't find the blocks in this blockchain: "+ name), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/blockTransactions")
    public ResponseEntity<?> getBlockTransactions(@RequestParam String hash, @RequestParam Integer limit) {

        return new ResponseEntity<>(new Error("Couldn't load transactions from the block with with this hash: "+ hash), HttpStatus.NOT_FOUND);
    }
}
