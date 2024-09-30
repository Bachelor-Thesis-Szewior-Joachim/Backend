package org.example.backend.blockchain.bitcoin.bitcoinBlock.entity;

import jakarta.persistence.ElementCollection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;

import java.util.List;

@Getter
@Setter
@Builder
public class BitcoinBlockDto {

    private Long id;
    private String hash;
    private int height;
    private String chain;
    private long total;
    private long fees;
    private int size;
    private int vsize;
    private int ver;
    private String time;
    private String received_time;
    private String relayed_by;
    private long bits;
    private long nonce;
    private int n_tx;
    private String previousBlock;
    private String merkle_root;
    private List<String> transactionIds;
    private String prev_block_url;
    private String tx_url;
    private String next_txids;
}
