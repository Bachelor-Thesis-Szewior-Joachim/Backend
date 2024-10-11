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
    private String prevHash;
    private int outputIndex;
    private int outputValue;
    private String scriptType;
    private String script;
    private List<String> addresses;
    private int sequence;
    private int age;
    private String walletName;
    private String walletToken;
}
