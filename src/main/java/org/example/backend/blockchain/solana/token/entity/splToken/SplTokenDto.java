package org.example.backend.blockchain.solana.token.entity.splToken;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SplTokenDto {
     private String amount;
     private Long decimals;
     private String uiAmount;
     private String uiAmountString;
}
