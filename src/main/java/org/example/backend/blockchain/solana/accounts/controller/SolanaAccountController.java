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
    public ResponseEntity<Long> getSolanaAccountBalance(@PathVariable String address) {
        Optional<Long> optionalBalance = solanaAccountService.getAccountBalance(address);

        return optionalBalance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Returns the 20 largest accounts, by lamport balance (results may be cached up to two hours).
    @GetMapping("/getLargestAccounts")
    public ResponseEntity<List<SolanaAccountDto>> getSolanaBiggestAccounts() {
        Optional<List<SolanaAccountDto>> optionalBalance = solanaAccountService.getSolanaBiggestAccounts();

        return optionalBalance.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to fetch account info
    @GetMapping("/{address}")
    public ResponseEntity<SolanaAccountDto> getSolanaAccount(@PathVariable String address) {
        Optional<SolanaAccountDto> solanaAccountDto = solanaAccountService.getAccountInfo(address);

        return solanaAccountDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Returns all accounts owned by the provided program Pubkey.
    @GetMapping("/{address}")
    public ResponseEntity<SolanaAccountDto> getProgramAccounts(@PathVariable String address) {
        Optional<SolanaAccountDto> solanaAccountDto = solanaAccountService.getProgramAccounts(address);

        return solanaAccountDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
