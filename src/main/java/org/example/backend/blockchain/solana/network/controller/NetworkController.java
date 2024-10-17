package org.example.backend.blockchain.solana.network.controller;

import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.example.backend.blockchain.solana.network.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping("/supply")
    public ResponseEntity<String> getSupply() {
        Optional<String> supplyDtoOptional = networkService.getSupply();

        return supplyDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/currentEpoch")
    public ResponseEntity<String> getCurrentEpochInfo() {
        Optional<String> epochDtoOptional = networkService.getCurrentEpochInfo();

        return epochDtoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/feeForMessage")
    public ResponseEntity<String> getFeeForMessage(@RequestParam String message) {
        Optional<String> feeMessageOptional = networkService.getFeeForMessage(message);

        return feeMessageOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genesisHash")
    public ResponseEntity<String> getGenesisHash() {
        Optional<String> genesisHashOptional = networkService.getGenesisHash();

        return genesisHashOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Get the fee the network will charge for a particular message
    @GetMapping("/firstAvailableBlock")
    public ResponseEntity<String> getFirstAvailableBlock() {
        Optional<String> firstAvailableBlockOptional = networkService.getFirstAvailableBlock();

        return firstAvailableBlockOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/minimumBalanceForRentExemption")
    public ResponseEntity<String> getMinimumBalanceForRentExemption(@RequestParam Long size) {
        Optional<String> minimumBalanceForRentExemptionOptional
                = networkService.getMinimumBalanceForRentExemption(size);

        return minimumBalanceForRentExemptionOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
