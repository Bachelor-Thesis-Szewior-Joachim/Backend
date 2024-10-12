package org.example.backend.blockchain.bitcoin.accounts.controller;

import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;
import org.example.backend.blockchain.bitcoin.accounts.service.BitcoinAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bitcoin/account")
public class BitcoinAccountController {

    private final BitcoinAccountService bitcoinAccountService;

    public BitcoinAccountController(BitcoinAccountService bitcoinAccountService) {
        this.bitcoinAccountService = bitcoinAccountService;
    }

    @GetMapping("/data/{address}")
    public ResponseEntity<BitcoinAccountDto> getAccountData(@PathVariable String address) {

        Optional<BitcoinAccountDto> optionalBitcoinAccountDto = Optional.ofNullable(bitcoinAccountService.getAllAccountData(address));
        if (optionalBitcoinAccountDto.isPresent()) {
            return ResponseEntity.ok(optionalBitcoinAccountDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
