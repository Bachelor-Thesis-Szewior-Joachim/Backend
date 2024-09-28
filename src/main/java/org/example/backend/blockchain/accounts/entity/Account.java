package org.example.backend.blockchain.accounts.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.client.entity.Client;
import org.example.backend.blockchain.wallet.entity.Wallet;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Account {

    @Id
    private int id;

    private String publicKey;
    private String privateKey;

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Client client;
    public Account() {

    }
}
