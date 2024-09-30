package org.example.backend.blockchain.bitcoin.accounts.controller;

import org.example.backend.blockchain.data.bitcoin.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitcoin")
public class BitcoinAccountController {

    private final BitcoinService bitcoinService;


    public BitcoinAccountController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    @GetMapping("/account/{address}")
    public ResponseEntity<?> getAccountInfo(@PathVariable String address) {
        String example = bitcoinService.getAllAccountData(address);
        return new ResponseEntity<>(example, HttpStatus.OK);
    }

    @GetMapping("/transaction/{address}")
    public ResponseEntity<?> getTransactionInfo(@PathVariable String address) {

        String example = bitcoinService.getTransactionByHash(address);
        return new ResponseEntity<>(example, HttpStatus.OK);
    }

    @GetMapping("/block/{address}")
    public ResponseEntity<?> getBlockInfo(@PathVariable String address) {

        String example = bitcoinService.getBitcoinBlockData(address);
        return new ResponseEntity<>(example, HttpStatus.OK);
    }

}
