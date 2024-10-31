package org.example.backend.blockchain.ethereum.accounts.entity;

import lombok.*;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class EthereumAccount {

    private String balance;
    private List<EthereumTransaction> transactions;
    public EthereumAccount() {

    }
}
