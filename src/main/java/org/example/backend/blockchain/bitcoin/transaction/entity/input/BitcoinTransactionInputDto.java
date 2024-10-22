package org.example.backend.blockchain.bitcoin.transaction.entity.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BitcoinTransactionInputDto {
    private String outputIndex;
    private String scriptType;
    private String script;
    private String sequence;
    private String age;

    public BitcoinTransactionInputDto() {

    }
}
