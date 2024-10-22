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
    private String blockHeight;
    private String blockIndex;
    private String hash;
    private List<String> addresses;
    private String total;
    private String fees;
    private String size;
    private String vsize;
    private String preference;
    private String confirmed;
    private String received;
    private String ver;
    private boolean doubleSpend;
    private String vinSz;
    private String voutSz;
    private String dataProtocol;
    private String confirmations;
    private float confidence;
    private List<BitcoinTransactionInput> inputs;
    private List<BitcoinTransactionOutput> outputs;

    public BitcoinTransaction() {

    }
}
