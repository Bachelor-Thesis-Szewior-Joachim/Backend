package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.accounts.service.EthereumAccountService;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EthereumAccountControllerTest {

    @Mock
    private EthereumAccountService ethereumAccountService;

    @InjectMocks
    private EthereumAccountController ethereumAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEtherBalanceAndTransactionHistory() {
        EthereumAccountDto ethereumAccountDto = new EthereumAccountDto();
        when(ethereumAccountService.getEtherBalanceAndTransactionHistory(anyString())).thenReturn(Optional.of(ethereumAccountDto));

        Optional<EthereumAccountDto> result = ethereumAccountController.GetEtherBalanceAndTransactionHistory("testAddress");

        assertTrue(result.isPresent());
        assertEquals(ethereumAccountDto, result.get());
    }

    @Test
    void getTokenBalance() {
        when(ethereumAccountService.getTokenBalance(anyString(), anyString())).thenReturn(100.0);

        ResponseEntity<Double> response = ethereumAccountController.getTokenBalance("testAddress", "testContractAddress");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(100.0, response.getBody());
    }

    @Test
    void getERC20TokenTransfers() {
        List<EthereumTransactionDto> transactions = List.of(new EthereumTransactionDto());
        when(ethereumAccountService.getERC20TokenTransfers(anyString(), anyInt(), anyInt(), anyString())).thenReturn(Optional.of(transactions));

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumAccountController.getERC20TokenTransfers("testAddress", 0, 99999999, "asc");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(transactions, response.getBody());
    }

    @Test
    void getERC721TokenTransfers() {
        List<EthereumTransactionDto> transactions = List.of(new EthereumTransactionDto());
        when(ethereumAccountService.getERC721TokenTransfers(anyString(), anyInt(), anyInt(), anyString())).thenReturn(Optional.of(transactions));

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumAccountController.getERC721TokenTransfers("testAddress", 0, 99999999, "asc");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(transactions, response.getBody());
    }

    @Test
    void getBlocksMinedByAddress() {
        when(ethereumAccountService.getBlocksMinedByAddress(anyString())).thenReturn("blocksMined");

        String result = ethereumAccountController.getBlocksMinedByAddress("testAddress");

        assertEquals("blocksMined", result);
    }
}