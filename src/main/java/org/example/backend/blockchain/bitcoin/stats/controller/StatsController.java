package org.example.backend.blockchain.bitcoin.stats.controller;

import org.example.backend.blockchain.bitcoin.stats.service.BitcoinStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bitcoin/stats")
public class StatsController {

    private final BitcoinStatsService bitcoinStatsService;

    public StatsController(BitcoinStatsService bitcoinStatsService) {
        this.bitcoinStatsService = bitcoinStatsService;
    }

    @GetMapping
    public ResponseEntity<String> getStats() {
        String stats = bitcoinStatsService.getBitcoinMainStatus();

        return ResponseEntity.ok(stats);
    }
}
