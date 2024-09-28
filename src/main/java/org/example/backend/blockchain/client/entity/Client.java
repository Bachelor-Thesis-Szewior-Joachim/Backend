package org.example.backend.blockchain.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.accounts.entity.Account;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Client {

    @Id
    private int id;
    private String username;
    private String password;

    @ElementCollection
    private List<String> seedPhrase = new LinkedList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Client() {

    }
}
