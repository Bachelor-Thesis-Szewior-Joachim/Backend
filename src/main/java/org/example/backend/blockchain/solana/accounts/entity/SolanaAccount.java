package org.example.backend.blockchain.solana.accounts.entity;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolanaAccount {

    private Long lamports;
    private Long slot;
    private String owner;
    private String data;
    private String state;
    private String coding;
    private boolean executable;
    private Long rentEpoch;
    private Long size;
}
