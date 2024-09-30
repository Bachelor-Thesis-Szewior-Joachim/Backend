package org.example.backend.blockchain.bitcoin.accounts.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.Transaction;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.wallet.entity.Wallet;

import java.util.List;

@Getter
@Setter
@Builder
public class BitcoinAccountDto {
    private String address;
    private long total_received;
    private long total_sent;
    private long balance;
    private long unconfirmed_balance;
    private long final_balance;
    private int n_tx;
    private int unconfirmed_n_tx;
    private int final_n_tx;
    private List<Transaction> transactions;
}
