package org.example.backend.blockchain.solana.slot.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotService {
    public Optional<String> getMinimumLedgerSlot() {

        return Optional.empty();
    }
}
