package org.example.backend.blockchain.solana.block.controller;

import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.example.backend.blockchain.solana.block.service.SolanaBlockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class SolanaBlockControllerTest {

    @Mock
    private SolanaBlockService solanaBlockService;

    @InjectMocks
    private SolanaBlockController solanaBlockController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBlockProduction() {
        Map<String, Object> blockProduction = Map.of("epoch", 1, "leaderSlots", 432);
        when(solanaBlockService.getBlockProduction()).thenReturn(Optional.of(blockProduction));

        ResponseEntity<Map<String, Object>> response = solanaBlockController.getBlockProduction();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blockProduction, response.getBody());
    }

    @Test
    public void testGetBlockProduction_NotFound() {
        when(solanaBlockService.getBlockProduction()).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = solanaBlockController.getBlockProduction();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBlockInfo() {
        long blockNumber = 12345L;
        SolanaBlockDto blockDto = new SolanaBlockDto();
        when(solanaBlockService.getBlock(blockNumber)).thenReturn(Optional.of(blockDto));

        ResponseEntity<SolanaBlockDto> response = solanaBlockController.getBlockInfo(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blockDto, response.getBody());
    }

    @Test
    public void testGetBlockInfo_NotFound() {
        long blockNumber = 12345L;
        when(solanaBlockService.getBlock(blockNumber)).thenReturn(Optional.empty());

        ResponseEntity<SolanaBlockDto> response = solanaBlockController.getBlockInfo(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBlockTime() {
        long blockNumber = 12345L;
        String blockTime = "2023-01-01T00:00:00Z";
        when(solanaBlockService.getBlockTime(blockNumber)).thenReturn(Optional.of(blockTime));

        ResponseEntity<String> response = solanaBlockController.getBlockTime(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blockTime, response.getBody());
    }

    @Test
    public void testGetBlockTime_NotFound() {
        long blockNumber = 12345L;
        when(solanaBlockService.getBlockTime(blockNumber)).thenReturn(Optional.empty());

        ResponseEntity<String> response = solanaBlockController.getBlockTime(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBlockCommitment() {
        long blockNumber = 12345L;
        Map<String, String> blockCommitment = Map.of("commitment", "finalized");
        when(solanaBlockService.getBlockCommitment(blockNumber)).thenReturn(Optional.of(blockCommitment));

        ResponseEntity<Map<String, String>> response = solanaBlockController.getBlockCommitment(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blockCommitment, response.getBody());
    }

    @Test
    public void testGetBlockCommitment_NotFound() {
        long blockNumber = 12345L;
        when(solanaBlockService.getBlockCommitment(blockNumber)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, String>> response = solanaBlockController.getBlockCommitment(blockNumber);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBlocksWithLimit() {
        Long startingBlock = 1000L;
        Long amount = 10L;
        String blocks = "blocks";
        when(solanaBlockService.getBlocksWithLimit(startingBlock, amount)).thenReturn(Optional.of(blocks));

        ResponseEntity<String> response = solanaBlockController.getBlocksWithLimit(startingBlock, amount);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blocks, response.getBody());
    }

    @Test
    public void testGetBlocksWithLimit_NotFound() {
        Long startingBlock = 1000L;
        Long amount = 10L;
        when(solanaBlockService.getBlocksWithLimit(startingBlock, amount)).thenReturn(Optional.empty());

        ResponseEntity<String> response = solanaBlockController.getBlocksWithLimit(startingBlock, amount);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetBlockHeight() {
        String blockHeight = "10000";
        when(solanaBlockService.getBlockHeight()).thenReturn(Optional.of(blockHeight));

        ResponseEntity<String> response = solanaBlockController.getBlockHeight();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blockHeight, response.getBody());
    }

    @Test
    public void testGetBlockHeight_NotFound() {
        when(solanaBlockService.getBlockHeight()).thenReturn(Optional.empty());

        ResponseEntity<String> response = solanaBlockController.getBlockHeight();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}