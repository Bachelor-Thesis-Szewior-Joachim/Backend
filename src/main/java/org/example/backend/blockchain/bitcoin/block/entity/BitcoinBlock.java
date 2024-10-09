package org.example.backend.blockchain.bitcoin.block.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinBlock {
    @Id
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
    @ElementCollection
    private List<String> transactionIds;
    private String prev_block_url;
    private String tx_url;
    private String next_txids;

    public BitcoinBlock() {

    }
}
