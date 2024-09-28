package org.example.backend.blockchain.client.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.accounts.entity.Account;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Builder
public class ClientDto {

    private int id;
    private String username;
    private String password;
    private List<String> seedPhrase = new LinkedList<>();
    private List<Account> accounts = new ArrayList<>();
}
