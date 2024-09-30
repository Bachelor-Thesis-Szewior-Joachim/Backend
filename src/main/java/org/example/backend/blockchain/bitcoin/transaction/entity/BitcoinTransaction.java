package org.example.backend.blockchain.bitcoin.transaction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinTransaction {

    private String hash;
    private int block_height;
    private long total;
    private long fees;
    private int size;
    private String relayed_by;
    private String confirmed;
    private String received;
    private List<Input> inputs;
    private List<Output> outputs;

    public BitcoinTransaction() {

    }
}
