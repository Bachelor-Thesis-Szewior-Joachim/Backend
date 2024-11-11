package org.example.backend.blockchain.bitcoin.accounts.mapper;

import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccount;
import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitcoinAccountMapperTest {

    @Test
    void testMapAccountDtoToAccount() {
        BitcoinAccountDto accountDto = BitcoinAccountDto.builder()
                .address("testAddress")
                .total_received(1000L)
                .total_sent(500L)
                .balance(500L)
                .unconfirmed_balance(100L)
                .final_balance(600L)
                .n_tx(10)
                .unconfirmed_n_tx(2)
                .final_n_tx(12)
                .transactions(null)
                .build();

        BitcoinAccount bitcoinAccount = BitcoinAccountMapper.mapAccountDtoToAccount(accountDto);

        assertEquals(accountDto.getAddress(), bitcoinAccount.getAddress());
        assertEquals(accountDto.getTotal_received(), bitcoinAccount.getTotal_received());
        assertEquals(accountDto.getTotal_sent(), bitcoinAccount.getTotal_sent());
        assertEquals(accountDto.getBalance(), bitcoinAccount.getBalance());
        assertEquals(accountDto.getUnconfirmed_balance(), bitcoinAccount.getUnconfirmed_balance());
        assertEquals(accountDto.getFinal_balance(), bitcoinAccount.getFinal_balance());
        assertEquals(accountDto.getN_tx(), bitcoinAccount.getN_tx());
        assertEquals(accountDto.getUnconfirmed_n_tx(), bitcoinAccount.getUnconfirmed_n_tx());
        assertEquals(accountDto.getFinal_n_tx(), bitcoinAccount.getFinal_n_tx());
        assertEquals(accountDto.getTransactions(), bitcoinAccount.getTransactions());
    }

    @Test
    void testMapAccountToAccountDto() {
        BitcoinAccount bitcoinAccount = BitcoinAccount.builder()
                .address("testAddress")
                .total_received(1000L)
                .total_sent(500L)
                .balance(500L)
                .unconfirmed_balance(100L)
                .final_balance(600L)
                .n_tx(10)
                .unconfirmed_n_tx(2)
                .final_n_tx(12)
                .transactions(null)
                .build();

        BitcoinAccountDto accountDto = BitcoinAccountMapper.mapAccountToAccountDto(bitcoinAccount);

        assertEquals(bitcoinAccount.getAddress(), accountDto.getAddress());
        assertEquals(bitcoinAccount.getTotal_received(), accountDto.getTotal_received());
        assertEquals(bitcoinAccount.getTotal_sent(), accountDto.getTotal_sent());
        assertEquals(bitcoinAccount.getBalance(), accountDto.getBalance());
        assertEquals(bitcoinAccount.getUnconfirmed_balance(), accountDto.getUnconfirmed_balance());
        assertEquals(bitcoinAccount.getFinal_balance(), accountDto.getFinal_balance());
        assertEquals(bitcoinAccount.getN_tx(), accountDto.getN_tx());
        assertEquals(bitcoinAccount.getUnconfirmed_n_tx(), accountDto.getUnconfirmed_n_tx());
        assertEquals(bitcoinAccount.getFinal_n_tx(), accountDto.getFinal_n_tx());
        assertEquals(bitcoinAccount.getTransactions(), accountDto.getTransactions());
    }
}