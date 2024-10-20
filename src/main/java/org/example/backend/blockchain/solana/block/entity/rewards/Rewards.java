package org.example.backend.blockchain.solana.block.entity.rewards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Rewards {
    private String commission;
    private Long lamports;
    private String postBalance;
    private String pubkey;
    private String rewardType;
}
