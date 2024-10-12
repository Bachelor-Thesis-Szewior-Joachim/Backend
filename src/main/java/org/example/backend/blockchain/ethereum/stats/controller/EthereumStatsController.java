package org.example.backend.blockchain.ethereum.stats.controller;

import org.example.backend.blockchain.ethereum.stats.service.EthereumStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ethereum/stats")
public class EthereumStatsController {
    private final EthereumStatsService statsService;

    public EthereumStatsController(EthereumStatsService statsService) {
        this.statsService = statsService;
    }

    // Endpoint to get the estimated gas needed for a transaction based on the specified gas price.
    @GetMapping("/gasEstimate/{gasPrice}")
    public ResponseEntity<String> getGasEstimate(@PathVariable long gasPrice) {
        String result = statsService.getGasEstimate(gasPrice);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get current gas price information.
    @GetMapping("/gasOracle")
    public ResponseEntity<String> getGasOracle() {
        String result = statsService.getGasOracle();
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the total circulating supply of Ether.
    @GetMapping("/ethSupply")
    public ResponseEntity<String> getEthSupply() {
        String result = statsService.getEthSupply();
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the Ethereum blockchain size between the specified start and end dates.
    @GetMapping("/chainSize")
    public ResponseEntity<String> getChainSize(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String clientType,
            @RequestParam String syncMode) {
        String result = statsService.getChainSize(startDate, endDate, clientType, syncMode);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the total number of active nodes on the Ethereum network.
    @GetMapping("/nodeCount")
    public ResponseEntity<String> getNodeCount() {
        String result = statsService.getNodeCount();
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
