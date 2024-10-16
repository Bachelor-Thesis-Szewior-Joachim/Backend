package org.example.backend.blockchain.solana.accounts.controller;

import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;
import org.example.backend.blockchain.solana.accounts.service.SolanaAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/solana/account")
public class SolanaAccountController {

    private final SolanaAccountService solanaAccountService;

    public SolanaAccountController(SolanaAccountService solanaAccountService) {
        this.solanaAccountService = solanaAccountService;
    }

    // Endpoint to fetch account info
    @GetMapping("/{address}")
    public ResponseEntity<SolanaAccountDto> getSolanaAccount(@PathVariable String address) {
        Optional<SolanaAccountDto> solanaAccountDto = solanaAccountService.getAccountInfo(address);

        // Check if data is present and return the appropriate response
        return solanaAccountDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
