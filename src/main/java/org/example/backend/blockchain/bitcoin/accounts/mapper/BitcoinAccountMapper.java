package org.example.backend.blockchain.bitcoin.accounts.mapper;

import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccount;
import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;

public class BitcoinAccountMapper {
    public static BitcoinAccount mapAccountDtoToAccount(BitcoinAccountDto accountDto) {
        return BitcoinAccount.builder()
                .address(accountDto.getAddress())
                .total_received(accountDto.getTotal_received())
                .total_sent(accountDto.getTotal_sent())
                .balance(accountDto.getBalance())
                .unconfirmed_balance(accountDto.getUnconfirmed_balance())
                .final_balance(accountDto.getFinal_balance())
                .n_tx(accountDto.getN_tx())
                .unconfirmed_n_tx(accountDto.getUnconfirmed_n_tx())
                .final_n_tx(accountDto.getFinal_n_tx())
                .transactions(accountDto.getTransactions())
                .build();
    }

    public static BitcoinAccountDto mapAccountToAccountDto(BitcoinAccount bitcoinAccount) {
        return BitcoinAccountDto.builder()
                .address(bitcoinAccount.getAddress())
                .total_received(bitcoinAccount.getTotal_received())
                .total_sent(bitcoinAccount.getTotal_sent())
                .balance(bitcoinAccount.getBalance())
                .unconfirmed_balance(bitcoinAccount.getUnconfirmed_balance())
                .final_balance(bitcoinAccount.getFinal_balance())
                .n_tx(bitcoinAccount.getN_tx())
                .unconfirmed_n_tx(bitcoinAccount.getUnconfirmed_n_tx())
                .final_n_tx(bitcoinAccount.getFinal_n_tx())
                .transactions(bitcoinAccount.getTransactions())
                .build();

    }
}
