package org.example.backend.blockchain.bitcoin.transaction.entity.transaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInput;
import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutput;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinTransaction {

    private String blockHash;
    private int blockHeight;
    private int blockIndex;
    private String hash;
    private List<List<String>> addresses;
    private long total;
    private long fees;
    private long size;
    private long vsize;
    private String preference;
    private String confirmed;
    private long received;
    private int ver;
    private boolean doubleSpend;
    private int vinSz;
    private int voutSz;
    private String dataProtocol;
    private int confirmations;
    private float confidence;
    private List<BitcoinTransactionInput> bitcoinTransactionInputs;
    private List<BitcoinTransactionOutput> bitcoinTransactionOutputs;

    public BitcoinTransaction() {

    }
}
