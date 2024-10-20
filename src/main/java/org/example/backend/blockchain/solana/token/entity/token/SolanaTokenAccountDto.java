package org.example.backend.blockchain.solana.token.entity.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SolanaTokenAccountDto {
    private String pubkey;
    private boolean executable;
    private long lamports;
    private String owner;
    private String rentEpoch;
    private int space;
    private String program;
    private int dataSpace;
    private boolean isNative;
    private String mint;
    private String accountOwner;
    private String state;
    private String amount;
    private int decimals;
    private double uiAmount;
    private String uiAmountString;
    private String type;
}
