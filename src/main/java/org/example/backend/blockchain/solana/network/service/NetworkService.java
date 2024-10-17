package org.example.backend.blockchain.solana.network.service;

import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class NetworkService {
    public Optional<SupplyDto> getSupply() {
        return Optional.empty();
    }

    public Optional<EpochDto> getCurrentEpochInfo() {

        return Optional.empty();
    }

    public Optional<Map<String, String>> getFeeForMessage(String message) {

        return Optional.empty();
    }

    public Optional<String> getGenesisHash() {

        return Optional.empty();

    }

    public Optional<Long> getFirstAvailableBlock() {

        return Optional.empty();
    }

    public Optional<Long> getMinimumBalanceForRentExemption() {

        return Optional.empty();
    }
}
