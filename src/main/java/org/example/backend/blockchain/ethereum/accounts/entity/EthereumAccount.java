package org.example.backend.blockchain.ethereum.accounts.entity;

import lombok.*;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.wallet.entity.Wallet;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class EthereumAccount {

    private String publicKey;
    private String privateKey;
    private Wallet wallet;
    private Client client;
    private String address;
    private String nameTag;
    private double balance;
    private double percentage;
    private Long totalCount;
    public EthereumAccount() {

    }
}
