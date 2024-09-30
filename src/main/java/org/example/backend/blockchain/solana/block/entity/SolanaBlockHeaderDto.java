package org.example.backend.blockchain.solana.block.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SolanaBlockHeaderDto {

    private Long id;
    private int version;
    private String previousBlockHash;
    private String merkleRoot;
    private long timestamp;
    private int difficultyTarget;
    private int nonce;
}
