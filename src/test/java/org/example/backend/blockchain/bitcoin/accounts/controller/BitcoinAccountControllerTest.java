package org.example.backend.blockchain.bitcoin.accounts.controller;


import org.example.backend.blockchain.bitcoin.accounts.entity.BitcoinAccountDto;
import org.example.backend.blockchain.bitcoin.accounts.service.BitcoinAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BitcoinAccountControllerTest {

    @Mock
    private BitcoinAccountService bitcoinAccountService;

    @InjectMocks
    private BitcoinAccountController bitcoinAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountData_Found() {
        String address = "testAddress";
        BitcoinAccountDto bitcoinAccountDto = new BitcoinAccountDto();
        when(bitcoinAccountService.getAllAccountData(address)).thenReturn(bitcoinAccountDto);

        ResponseEntity<BitcoinAccountDto> response = bitcoinAccountController.getAccountData(address);

        assertEquals(ResponseEntity.ok(bitcoinAccountDto), response);
        verify(bitcoinAccountService, times(1)).getAllAccountData(address);
    }

    @Test
    void testGetAccountData_NotFound() {
        String address = "testAddress";
        when(bitcoinAccountService.getAllAccountData(address)).thenReturn(null);

        ResponseEntity<BitcoinAccountDto> response = bitcoinAccountController.getAccountData(address);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(bitcoinAccountService, times(1)).getAllAccountData(address);
    }
}