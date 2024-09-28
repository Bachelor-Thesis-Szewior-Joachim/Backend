package org.example.backend.blockchain.accounts.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.client.entity.Client;
import org.example.backend.blockchain.wallet.entity.Wallet;

@Getter
@Setter
@Builder
public class AccountDto {
    private int id;
    private String publicKey;
    private String privateKey;
    private Wallet wallet;
    private Client client;
}
