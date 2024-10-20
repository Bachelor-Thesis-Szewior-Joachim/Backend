package org.example.backend.blockchain.solana.network.entity.epoch;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EpochDto {
    private String absoluteSlot;
    private String blockHeight;
    private String epoch;
    private String slotIndex;
    private String slotsInEpoch;
    private String transactionCount;
}

