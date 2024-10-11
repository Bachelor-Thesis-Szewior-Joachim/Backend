package org.example.backend.blockchain.bitcoin.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WalletHD {
    private String name;
    private String token;
    private String extendedPublicKey;
    private List<Integer> subchainIndexes;
}
