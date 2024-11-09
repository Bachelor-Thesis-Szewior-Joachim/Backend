package org.example.backend.blockchain.solana.transaction.controller;


import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress.SignatureForAddressDto;
import org.example.backend.blockchain.solana.transaction.service.SolanaTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/solana/transaction")
public class SolanaTransactionController {

    private final SolanaTransactionService solanaTransactionService;

    public SolanaTransactionController(SolanaTransactionService solanaTransactionService) {
        this.solanaTransactionService = solanaTransactionService;
    }

    @GetMapping("/{signature}")
    public ResponseEntity<SolanaTransactionDto> getTransactionInfo(@PathVariable String signature) {
        Optional<SolanaTransactionDto> solanaTransactionDtoOptional = solanaTransactionService.getTransaction(signature);

        return solanaTransactionDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/signatures")
    public ResponseEntity<Map<String, String>> getSignatureStatuses(@RequestParam List<String> signatures) {
        Optional<Map<String, String>> solanaTransactionServiceSignatureStatusesOptional = solanaTransactionService.getSignatureStatuses(signatures);

        return solanaTransactionServiceSignatureStatusesOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Returns signatures for confirmed transactions that include the given address in their accountKeys list.
    // Returns signatures backwards in time from the provided signature or most recent confirmed block.
    @GetMapping("/signaturesForAddress/{signature}")
    public ResponseEntity<List<SignatureForAddressDto>> getSignaturesForAddress(@PathVariable String signature) {
        Optional<List<SignatureForAddressDto>> solanaTransactionDtoOptional = solanaTransactionService.getSignaturesForAddress(signature);

        return solanaTransactionDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
