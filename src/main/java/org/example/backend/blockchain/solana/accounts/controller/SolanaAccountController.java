package org.example.backend.blockchain.solana.accounts.controller;

import org.example.backend.blockchain.data.bitcoin.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolanaAccountController {

    private final BitcoinService bitcoinService;


    public SolanaAccountController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }
//    @GetMapping("/{blockchain}/{address}")
//    public ResponseEntity<?> getAccountInfo(@PathVariable String blockchain, @PathVariable String address) {
//        String example = bitcoinService.getAllAccountData(address);
//        return new ResponseEntity<>(example, HttpStatus.OK);
//    }


}
