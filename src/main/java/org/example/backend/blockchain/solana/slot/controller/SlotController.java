package org.example.backend.blockchain.solana.slot.controller;

import org.example.backend.blockchain.solana.slot.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/solana/slot")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/minimum")
    public ResponseEntity<String> getMinimumLedgerSlot() {
        Optional<String> optionalMinimum = slotService.getMinimumLedgerSlot();

        return optionalMinimum.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
