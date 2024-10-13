package org.example.backend.blockchain.ethereum.stats.controller;

import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSizeDto;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracleDto;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCountDto;
import org.example.backend.blockchain.ethereum.stats.service.EthereumStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<String> gasEstimate = statsService.getGasEstimate(gasPrice);
        if (gasEstimate.isPresent()) {
            return ResponseEntity.ok(gasEstimate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get current gas price information.
    @GetMapping("/gasOracle")
    public ResponseEntity<GasOracleDto> getGasOracle() {
        Optional<GasOracleDto> gasOracleDtoOptional = statsService.getGasOracle();
        if (gasOracleDtoOptional.isPresent()) {
            return ResponseEntity.ok(gasOracleDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the total circulating supply of Ether.
    @GetMapping("/ethSupply")
    public ResponseEntity<String> getEthSupply() {
        Optional<String> ethSupplyOptional = statsService.getEthSupply();
        if (ethSupplyOptional.isPresent()) {
            return ResponseEntity.ok(ethSupplyOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the Ethereum blockchain size between the specified start and end dates.
    @GetMapping("/chainSize")
    public ResponseEntity<List<ChainSizeDto>> getChainSize(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String clientType,
            @RequestParam String syncMode) {
        Optional<List<ChainSizeDto>> optionalChainSizeDtos = Optional.ofNullable(statsService.getChainSize(startDate, endDate, clientType, syncMode));
        if (optionalChainSizeDtos.isPresent()) {
            return ResponseEntity.ok(optionalChainSizeDtos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get the total number of active nodes on the Ethereum network.
    @GetMapping("/nodeCount")
    public ResponseEntity<NodeCountDto> getNodeCount() {
        Optional<NodeCountDto> optionalNodeCountDto = Optional.ofNullable(statsService.getNodeCount());
        if (optionalNodeCountDto.isPresent()) {
            return ResponseEntity.ok(optionalNodeCountDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
