package org.example.backend.blockchain.solana.accounts.entity;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolanaAccountDto {
    private Long lamports;
    private Long slot;
    private String owner;
    private String data;
    private String coding;
    private String state;
    private boolean executable;
    private String rentEpoch;
    private Long space;


}
