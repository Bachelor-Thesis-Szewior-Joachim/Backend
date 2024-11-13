package org.example.backend.blockchain.bitcoin.transaction.entity.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinTransactionInput {
    private String outputIndex;
    private String scriptType;
    private String script;
    private String sequence;
    private String age;
}
