package org.example.backend.blockchain.ethereum.token.controller;

import org.example.backend.blockchain.ethereum.token.service.EthereumTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/ethereum/token")
public class EthereumTokensController {

    private final EthereumTokenService ethereumTokenService;

    public EthereumTokensController(EthereumTokenService ethereumTokenService) {
        this.ethereumTokenService = ethereumTokenService;
    }

    @GetMapping("tokenSupplyBy/{address}")
    public ResponseEntity<String> tokenSupplyBy(@PathVariable String address) {
        Optional<String> optionalTokenSupplyBy = ethereumTokenService.getTokenSupply(address);

        if (optionalTokenSupplyBy.isPresent()) {
            return ResponseEntity.ok(optionalTokenSupplyBy.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
