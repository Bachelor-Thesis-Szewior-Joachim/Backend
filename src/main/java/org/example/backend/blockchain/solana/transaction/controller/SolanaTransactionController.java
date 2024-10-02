package org.example.backend.blockchain.solana.transaction.controller;


import org.example.backend.blockchain.solana.transaction.service.SolanaTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solana/transaction")
public class SolanaTransactionController {

    private final SolanaTransactionService solanaTransactionService;

    public SolanaTransactionController(SolanaTransactionService solanaTransactionService) {
        this.solanaTransactionService = solanaTransactionService;
    }

    @GetMapping("/{signature}")
    public String getTransactionInfo(@PathVariable String signature) {
        try {
            return solanaTransactionService.getTransaction(signature);
        } catch (Exception e) {
            return "Error fetching transaction info: " + e.getMessage();
        }
    }
}
