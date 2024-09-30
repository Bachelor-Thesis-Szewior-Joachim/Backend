package org.example.backend.blockchain.solana.accounts.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.wallet.entity.Wallet;

@Getter
@Setter
@Builder
public class SolanaAccountDto {
    private Long id;
    private String publicKey;
    private String privateKey;
    private Wallet wallet;
    private Client client;
    private String address;
    private String nameTag;
    private double balance;
    private double percentage;
    private Long totalCount;
}
