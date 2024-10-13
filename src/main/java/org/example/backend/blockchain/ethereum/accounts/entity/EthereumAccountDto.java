package org.example.backend.blockchain.ethereum.accounts.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.example.backend.client.wallet.entity.Wallet;

import java.util.List;

@Getter
@Setter
@Builder
public class EthereumAccountDto {
    private String balance;
    private List<EthereumTransactionDto> transactions;
}
