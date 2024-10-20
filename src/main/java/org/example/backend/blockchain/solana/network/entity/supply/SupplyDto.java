package org.example.backend.blockchain.solana.network.entity.supply;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplyDto {
    String circulating;
    String notCirculating;
    String total;
}
