package org.example.backend.blockchain.solana.transaction.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessageDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaTransactionDto {
    private List<String> signatures;
    private SolanaTransactionMessageDto message;

}
