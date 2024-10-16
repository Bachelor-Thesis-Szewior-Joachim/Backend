package org.example.backend.blockchain.solana.token.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.token.entity.uiTokenAmount.UiTokenAmount;

import java.util.HexFormat;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SolanaTokenEntity {
    private String accountIndex;
    private String mint;
    private String owner;
    private String programId;
    private UiTokenAmount uiTokenAmount;
}
