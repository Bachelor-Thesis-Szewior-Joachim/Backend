package org.example.backend.blockchain.bitcoin.accounts.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinAccount {

    private String address;
    private long total_received;
    private long total_sent;
    private long balance;
    private long unconfirmed_balance;
    private long final_balance;
    private int n_tx;
    private int unconfirmed_n_tx;
    private int final_n_tx;
    private List<BitcoinTransaction> transactions;

    public BitcoinAccount() {

    }
}
