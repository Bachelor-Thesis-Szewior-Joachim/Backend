package org.example.backend.blockchain.solana.token.controller;

import org.example.backend.blockchain.solana.token.entity.splToken.SplTokenDto;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccountDto;
import org.example.backend.blockchain.solana.token.service.SolanaTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/solana/token")
public class SolanaTokenController {

    private final SolanaTokenService solanaTokenService;

    public SolanaTokenController(SolanaTokenService solanaTokenService) {
        this.solanaTokenService = solanaTokenService;
    }

    @GetMapping("/tokenAccountByOwner/{address}")
    public ResponseEntity<SolanaTokenAccountDto> getTokenAccountsByOwner(@PathVariable String address, @RequestParam String option, @RequestParam String pubkey) {
        Optional<SolanaTokenAccountDto> dataOptional = solanaTokenService.getTokenAccountsByOwner(address, option, pubkey);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tokenAccountBalance/{address}")
    public ResponseEntity<SplTokenDto> getTokenAccountBalance(@PathVariable String address) {
        Optional<SplTokenDto> dataOptional = solanaTokenService.getTokenAccountBalance(address);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tokenSupply/{address}")
    public ResponseEntity<SplTokenDto> getTokenSupply(@PathVariable String address) {
        Optional<SplTokenDto> dataOptional = solanaTokenService.getTokenSupply(address);

        return dataOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
