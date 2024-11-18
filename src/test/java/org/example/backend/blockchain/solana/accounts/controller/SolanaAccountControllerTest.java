package org.example.backend.blockchain.solana.accounts.controller;

import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;
import org.example.backend.blockchain.solana.accounts.service.SolanaAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SolanaAccountControllerTest {

    @Mock
    private SolanaAccountService solanaAccountService;

    @InjectMocks
    private SolanaAccountController solanaAccountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSolanaAccountBalance() {
        String address = "someAddress";
        String balance = "1000";
        when(solanaAccountService.getAccountBalance(address)).thenReturn(Optional.of(balance));

        ResponseEntity<String> response = solanaAccountController.getSolanaAccountBalance(address);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balance, response.getBody());
    }

    @Test
    public void testGetSolanaAccountBalance_NotFound() {
        String address = "someAddress";
        when(solanaAccountService.getAccountBalance(address)).thenReturn(Optional.empty());

        ResponseEntity<String> response = solanaAccountController.getSolanaAccountBalance(address);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetSolanaAccount() {
        String address = "someAddress";
        SolanaAccountDto accountDto = new SolanaAccountDto();
        when(solanaAccountService.getAccountInfo(address)).thenReturn(Optional.of(accountDto));

        ResponseEntity<SolanaAccountDto> response = solanaAccountController.getSolanaAccount(address);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
    }

    @Test
    public void testGetSolanaAccount_NotFound() {
        String address = "someAddress";
        when(solanaAccountService.getAccountInfo(address)).thenReturn(Optional.empty());

        ResponseEntity<SolanaAccountDto> response = solanaAccountController.getSolanaAccount(address);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}