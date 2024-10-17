package org.example.backend.blockchain.solana.token.controller;

import org.example.backend.blockchain.solana.token.service.SolanaTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/solana/token")
public class SolanaTokenController {

    private final SolanaTokenService solanaTokenService;

    public SolanaTokenController(SolanaTokenService solanaTokenService) {
        this.solanaTokenService = solanaTokenService;
    }

    @GetMapping("/tokenAccountByOwner/{address}")
    public ResponseEntity<String> getTokenAccountsByOwner(@PathVariable String address) {
        Optional<String> dataOptional = solanaTokenService.getTokenAccountsByOwner(address);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tokenAccountBalance/{address}")
    public ResponseEntity<String> getTokenAccountBalance(@PathVariable String address) {
        Optional<String> dataOptional = solanaTokenService.getTokenAccountBalance(address);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tokenSupply/{address}")
    public ResponseEntity<String> getTokenSupply(@PathVariable String address) {
        Optional<String> dataOptional = solanaTokenService.getTokenSupply(address);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
