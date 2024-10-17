package org.example.backend.blockchain.solana.network.controller;

import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.example.backend.blockchain.solana.network.service.NetworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/solana/network")
public class NetworkController {

    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping("/supply")
    public ResponseEntity<SupplyDto> getSupply() {
        Optional<SupplyDto> supplyDtoOptional = networkService.getSupply();

        return supplyDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/currentEpoch")
    public ResponseEntity<EpochDto> getCurrentEpochInfo() {
        Optional<EpochDto> epochDtoOptional = networkService.getCurrentEpochInfo();

        return epochDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/feeForMessage")
    public ResponseEntity<Map<String, String>> getFeeForMessage(@RequestParam String message) {
        Optional<Map<String, String>> feeMessageOptional = networkService.getFeeForMessage(message);

        return feeMessageOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genesisHash")
    public ResponseEntity<String> getGenesisHash() {
        Optional<String> genesisHashOptional = networkService.getGenesisHash();

        return genesisHashOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Get the fee the network will charge for a particular message
    @GetMapping("/firstAvailableBlock")
    public ResponseEntity<Long> getFirstAvailableBlock() {
        Optional<Long> firstAvailableBlockOptional = networkService.getFirstAvailableBlock();

        return firstAvailableBlockOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/minimumBalanceForRentExemption")
    public ResponseEntity<Long> getMinimumBalanceForRentExemption() {
        Optional<Long> minimumBalanceForRentExemptionOptional
                = networkService.getMinimumBalanceForRentExemption();

        return minimumBalanceForRentExemptionOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
