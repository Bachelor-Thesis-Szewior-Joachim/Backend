package org.example.backend.blockchain.solana.transaction.entity.transaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessage;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaTransaction {

    private List<String> signatures;
    private SolanaTransactionMessage message;

    public SolanaTransaction() {

    }
}
