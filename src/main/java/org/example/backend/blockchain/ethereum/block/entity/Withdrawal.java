package org.example.backend.blockchain.ethereum.block.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Withdrawal {
    private String index;
    private String validatorIndex;
    private String address;
    private String amount;

    public Withdrawal() {

    }
}
