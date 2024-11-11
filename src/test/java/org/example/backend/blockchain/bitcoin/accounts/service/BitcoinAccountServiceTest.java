package org.example.backend.blockchain.bitcoin.accounts.service;

import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccount;
import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;
import org.example.backend.blockchain.bitcoin.accounts.mapper.BitcoinAccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BitcoinAccountServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BitcoinAccountService bitcoinAccountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccountData_Found() {
        String address = "testAddress";
        BitcoinAccount bitcoinAccount = BitcoinAccount.builder()
                .address(address)
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
        BitcoinAccountDto bitcoinAccountDto = BitcoinAccountMapper.mapAccountToAccountDto(bitcoinAccount);

        when(restTemplate.getForObject(anyString(), eq(BitcoinAccount.class))).thenReturn(bitcoinAccount);

        BitcoinAccountDto result = bitcoinAccountService.getAllAccountData(address);

        assertEquals(bitcoinAccountDto.getAddress(), result.getAddress());
        assertEquals(bitcoinAccountDto.getTotal_received(), result.getTotal_received());
        assertEquals(bitcoinAccountDto.getTotal_sent(), result.getTotal_sent());
        assertEquals(bitcoinAccountDto.getBalance(), result.getBalance());
        assertEquals(bitcoinAccountDto.getUnconfirmed_balance(), result.getUnconfirmed_balance());
        assertEquals(bitcoinAccountDto.getFinal_balance(), result.getFinal_balance());
        assertEquals(bitcoinAccountDto.getN_tx(), result.getN_tx());
        assertEquals(bitcoinAccountDto.getUnconfirmed_n_tx(), result.getUnconfirmed_n_tx());
        assertEquals(bitcoinAccountDto.getFinal_n_tx(), result.getFinal_n_tx());
        assertEquals(bitcoinAccountDto.getTransactions(), result.getTransactions());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(BitcoinAccount.class));
    }


    @Test
    void testGetAllAccountData_NotFound() {
        String address = "testAddress";

        when(restTemplate.getForObject(anyString(), eq(BitcoinAccount.class))).thenReturn(null);

        BitcoinAccountDto result = bitcoinAccountService.getAllAccountData(address);

        assertNull(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(BitcoinAccount.class));
    }
}