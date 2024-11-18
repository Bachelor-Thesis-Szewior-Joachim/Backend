package org.example.backend.blockchain.ethereum.block.controller;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;
import org.example.backend.blockchain.ethereum.block.service.EthereumBlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class EthereumBlockControllerTest {

    @Mock
    private EthereumBlockService ethereumBlockService;

    @InjectMocks
    private EthereumBlockController ethereumBlockController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBlock() {
        Long blockNumber = 12345L;
        EthereumBlockDto ethereumBlockDto = new EthereumBlockDto();
        when(ethereumBlockService.getBlockByNumber(blockNumber)).thenReturn(Optional.of(ethereumBlockDto));

        ResponseEntity<EthereumBlockDto> response = ethereumBlockController.getBlock(blockNumber);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ethereumBlockDto, response.getBody());
    }

    @Test
    public void testGetMinedBlocks() {
        String address = "0x123";
        List<EthereumBlockDto> ethereumBlockDtos = Arrays.asList(new EthereumBlockDto(), new EthereumBlockDto());
        when(ethereumBlockService.getMinedBlocks(address, "blocks")).thenReturn(Optional.of(ethereumBlockDtos));

        ResponseEntity<List<EthereumBlockDto>> response = ethereumBlockController.getMinedBlocks(address, "blocks");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ethereumBlockDtos, response.getBody());
    }

    @Test
    public void testGetEthSupply() {
        String ethSupply = "1000000";
        when(ethereumBlockService.getEthSupply()).thenReturn(Optional.of(ethSupply));

        ResponseEntity<String> response = ethereumBlockController.getEthSupply();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ethSupply, response.getBody());
    }

    @Test
    public void testGetEthPrice() {
        String ethPrice = "2000";
        when(ethereumBlockService.getEthPrice()).thenReturn(Optional.of(ethPrice));

        ResponseEntity<String> response = ethereumBlockController.getEthPrice();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(ethPrice, response.getBody());
    }
}