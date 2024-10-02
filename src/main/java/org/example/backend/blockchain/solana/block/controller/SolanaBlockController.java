package org.example.backend.blockchain.solana.block.controller;

import org.example.backend.blockchain.solana.block.service.SolanaBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solana/block")
public class SolanaBlockController {


    private final SolanaBlockService solanaBlockService;

    public SolanaBlockController(SolanaBlockService solanaBlockService) {
        this.solanaBlockService = solanaBlockService;
    }
    @GetMapping("/{blockNumber}")
    public String getBlockInfo(@PathVariable long blockNumber) {
        try {
            return solanaBlockService.getBlock(blockNumber);
        } catch (Exception e) {
            return "Error fetching block info: " + e.getMessage();
        }
    }
}
