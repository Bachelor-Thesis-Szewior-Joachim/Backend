package org.example.backend.blockchain.accounts.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.user.entity.User;
import org.example.backend.blockchain.wallet.entity.Wallet;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
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
    private User user;
    public Account() {

    }
}
