package org.example.backend.blockchain.solana.node.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolanaClusterNode {
    private long featureSet;
    private String gossip;
    private String pubkey;
    private String pubsub;
    private String rpc;
    private String serveRepair;
    private int shredVersion;
    private String tpu;
    private String tpuForwards;
    private String tpuForwardsQuic;
    private String tpuQuic;
    private String tpuVote;
    private String tvu;
    private String version;
}
