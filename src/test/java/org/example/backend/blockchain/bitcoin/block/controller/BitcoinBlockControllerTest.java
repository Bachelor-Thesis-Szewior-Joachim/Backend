package org.example.backend.blockchain.bitcoin.block.controller;

import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlockDto;
import org.example.backend.blockchain.bitcoin.block.service.BitcoinBlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BitcoinBlockControllerTest {

    @Mock
    private BitcoinBlockService bitcoinBlockService;

    @InjectMocks
    private BitcoinBlockController bitcoinBlockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBlockData_Found() {
        String blockHash = "testBlockHash";
        BitcoinBlockDto bitcoinBlockDto = new BitcoinBlockDto();
        when(bitcoinBlockService.getBitcoinBlockData(blockHash)).thenReturn(bitcoinBlockDto);

        ResponseEntity<BitcoinBlockDto> response = bitcoinBlockController.getBlockData(blockHash);

        assertEquals(ResponseEntity.ok(bitcoinBlockDto), response);
        verify(bitcoinBlockService, times(1)).getBitcoinBlockData(blockHash);
    }

    @Test
    void testGetBlockData_NotFound() {
        String blockHash = "testBlockHash";
        when(bitcoinBlockService.getBitcoinBlockData(blockHash)).thenReturn(null);

        ResponseEntity<BitcoinBlockDto> response = bitcoinBlockController.getBlockData(blockHash);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(bitcoinBlockService, times(1)).getBitcoinBlockData(blockHash);
    }
}