package org.example.backend.blockchain.solana.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.block.entity.rewards.Rewards;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaBlock {

   private String blockHeight;
   private String blockTime;
   private String blockHash;
   private String parentSlot;
   private String previousHash;
   private Rewards rewards;
   private List<String> transactions;
}
