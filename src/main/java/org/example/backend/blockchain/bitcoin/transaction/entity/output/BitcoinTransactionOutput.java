package org.example.backend.blockchain.bitcoin.transaction.entity.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinTransactionOutput {
    private int value;
    private String script;
    private List<String> addresses;
    private String scriptType;
    private String spentBy;
    private String dataHex;
    private String dataString;
}
