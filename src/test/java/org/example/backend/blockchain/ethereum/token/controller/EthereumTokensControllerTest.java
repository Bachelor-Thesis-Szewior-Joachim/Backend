package org.example.backend.blockchain.ethereum.token.controller;


import org.example.backend.blockchain.ethereum.token.service.EthereumTokenService;
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

public class EthereumTokensControllerTest {

    @Mock
    private EthereumTokenService ethereumTokenService;

    @InjectMocks
    private EthereumTokensController ethereumTokensController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTokenSupplyBy() {
        String address = "0x123";
        String tokenSupply = "1000";
        when(ethereumTokenService.getTokenSupply(address)).thenReturn(Optional.of(tokenSupply));

        ResponseEntity<String> response = ethereumTokensController.tokenSupplyBy(address);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tokenSupply, response.getBody());
    }

    @Test
    public void testTokenSupplyBy_NotFound() {
        String address = "0x123";
        when(ethereumTokenService.getTokenSupply(address)).thenReturn(Optional.empty());

        ResponseEntity<String> response = ethereumTokensController.tokenSupplyBy(address);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}