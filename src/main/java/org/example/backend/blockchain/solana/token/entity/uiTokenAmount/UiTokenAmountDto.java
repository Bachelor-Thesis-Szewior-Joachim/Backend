package org.example.backend.blockchain.solana.token.entity.uiTokenAmount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UiTokenAmountDto {
    private String amount;
    private Long decimals;
    private String uiAmount;
    private String uiAmountString;
}
