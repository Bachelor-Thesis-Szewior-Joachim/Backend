package org.example.backend.blockchain.solana.accounts.controller;

import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;
import org.example.backend.blockchain.solana.accounts.service.SolanaAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solana/account")
public class SolanaAccountController {

    private final SolanaAccountService solanaAccountService;

    public SolanaAccountController(SolanaAccountService solanaAccountService) {
        this.solanaAccountService = solanaAccountService;
    }

    //Get account balance
    @GetMapping("/balance/{address}")
    public ResponseEntity<String> getSolanaAccountBalance(@PathVariable String address) {
        Optional<String> optionalBalance = solanaAccountService.getAccountBalance(address);

        return optionalBalance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to fetch account info
    @GetMapping("/{address}")
    public ResponseEntity<String> getSolanaAccount(@PathVariable String address) {
        Optional<String> solanaAccountDto = solanaAccountService.getAccountInfo(address);

        return solanaAccountDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
