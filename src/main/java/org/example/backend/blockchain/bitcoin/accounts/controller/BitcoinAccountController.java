package org.example.backend.blockchain.bitcoin.accounts.controller;

import org.example.backend.blockchain.bitcoin.accounts.service.BitcoinAccountService;
import org.example.backend.blockchain.data.bitcoin.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitcoin/account")
public class BitcoinAccountController {

    private final BitcoinAccountService bitcoinAccountService;

    public BitcoinAccountController(BitcoinAccountService bitcoinAccountService) {
        this.bitcoinAccountService = bitcoinAccountService;
    }

    @GetMapping("/data/{address}")
    public String getAccountData(@PathVariable String address) {
        return bitcoinAccountService.getAllAccountData(address);
    }

}
