package org.example.backend.blockchain.solana.network.entity.supply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Supply {
    String circulating;
    String notCirculating;
    String total;
}
