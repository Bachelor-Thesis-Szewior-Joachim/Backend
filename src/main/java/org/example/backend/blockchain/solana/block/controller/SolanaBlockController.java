package org.example.backend.blockchain.solana.block.controller;

import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.example.backend.blockchain.solana.block.service.SolanaBlockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/solana/block")
public class SolanaBlockController {


    private final SolanaBlockService solanaBlockService;

    public SolanaBlockController(SolanaBlockService solanaBlockService) {
        this.solanaBlockService = solanaBlockService;
    }

    //Returns recent block production information from the current or previous epoch.
    @GetMapping("/blockProduction")
    public ResponseEntity<Map<String, Object>> getBlockProduction() {
        Optional<Map<String, Object>> solanaBlockDtoOptional = solanaBlockService.getBlockProduction();
            return solanaBlockDtoOptional.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    //Returns the block information
    @GetMapping("/{blockNumber}")
    public ResponseEntity<SolanaBlockDto> getBlockInfo(@PathVariable long blockNumber) {
        Optional<SolanaBlockDto> solanaBlockDtoOptional = solanaBlockService.getBlock(blockNumber);
        return solanaBlockDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Returns the estimated production time of a block
    @GetMapping("/time/{blockNumber}")
    public ResponseEntity<String> getBlockTime(@PathVariable long blockNumber) {
        Optional<String> solanaBlockDtoOptional = solanaBlockService.getBlockTime(blockNumber);
        return solanaBlockDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Returns the estimated production time of a block
    @GetMapping("/commitment/{blockNumber}")
    public ResponseEntity<Map<String, String>> getBlockCommitment(@PathVariable long blockNumber) {
        Optional<Map<String, String>> solanaBlockDtoOptional = solanaBlockService.getBlockCommitment(blockNumber);
        return solanaBlockDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Returns a list of confirmed blocks starting at the given slot
    @GetMapping("/getBlocksWithLimit")
    public ResponseEntity<String> getBlocksWithLimit(@RequestParam Long startingBlock,
                                                                   @RequestParam Long amount) {
        Optional<String> solanaBlockDtoOptional = solanaBlockService.getBlocksWithLimit(startingBlock, amount);
        return solanaBlockDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Returns the current block height of the node
    @GetMapping("/getBlockHeight")
    public ResponseEntity<String> getBlockHeight() {
        Optional<String> solanaBlockDtoOptional = solanaBlockService.getBlockHeight();
        return solanaBlockDtoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
