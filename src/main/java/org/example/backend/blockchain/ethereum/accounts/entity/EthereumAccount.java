package org.example.backend.blockchain.ethereum.accounts.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.wallet.entity.Wallet;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumAccount {

    @Id
    private Long id;

    private String publicKey;
    private String privateKey;

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String address;
    private String nameTag;
    private double balance;
    private double percentage;
    private Long totalCount;
    public EthereumAccount() {

    }
}
