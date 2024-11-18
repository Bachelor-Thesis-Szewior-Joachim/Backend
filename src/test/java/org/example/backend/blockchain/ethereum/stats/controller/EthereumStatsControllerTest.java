package org.example.backend.blockchain.ethereum.stats.controller;

import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSizeDto;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracleDto;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCountDto;
import org.example.backend.blockchain.ethereum.stats.service.EthereumStatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class EthereumStatsControllerTest {

    @Mock
    private EthereumStatsService statsService;

    @InjectMocks
    private EthereumStatsController ethereumStatsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGasEstimate() {
        long gasPrice = 100L;
        String gasEstimate = "21000";
        when(statsService.getGasEstimate(gasPrice)).thenReturn(Optional.of(gasEstimate));

        ResponseEntity<String> response = ethereumStatsController.getGasEstimate(gasPrice);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gasEstimate, response.getBody());
    }

    @Test
    public void testGetGasEstimate_NotFound() {
        long gasPrice = 100L;
        when(statsService.getGasEstimate(gasPrice)).thenReturn(Optional.empty());

        ResponseEntity<String> response = ethereumStatsController.getGasEstimate(gasPrice);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetGasOracle() {
        GasOracleDto gasOracleDto = new GasOracleDto();
        when(statsService.getGasOracle()).thenReturn(Optional.of(gasOracleDto));

        ResponseEntity<GasOracleDto> response = ethereumStatsController.getGasOracle();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(gasOracleDto, response.getBody());
    }

    @Test
    public void testGetGasOracle_NotFound() {
        when(statsService.getGasOracle()).thenReturn(Optional.empty());

        ResponseEntity<GasOracleDto> response = ethereumStatsController.getGasOracle();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetEthSupply() {
        String ethSupply = "1000000";
        when(statsService.getEthSupply()).thenReturn(Optional.of(ethSupply));

        ResponseEntity<String> response = ethereumStatsController.getEthSupply();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ethSupply, response.getBody());
    }

    @Test
    public void testGetEthSupply_NotFound() {
        when(statsService.getEthSupply()).thenReturn(Optional.empty());

        ResponseEntity<String> response = ethereumStatsController.getEthSupply();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetChainSize() {
        List<ChainSizeDto> chainSizeDtos = List.of(new ChainSizeDto(), new ChainSizeDto());
        when(statsService.getChainSize("2021-01-01", "2021-12-31", "geth", "fast")).thenReturn(chainSizeDtos);

        ResponseEntity<List<ChainSizeDto>> response = ethereumStatsController.getChainSize("2021-01-01", "2021-12-31", "geth", "fast");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(chainSizeDtos, response.getBody());
    }

    @Test
    public void testGetChainSize_NotFound() {
        when(statsService.getChainSize("2021-01-01", "2021-12-31", "geth", "fast")).thenReturn(null);

        ResponseEntity<List<ChainSizeDto>> response = ethereumStatsController.getChainSize("2021-01-01", "2021-12-31", "geth", "fast");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetNodeCount() {
        NodeCountDto nodeCountDto = new NodeCountDto();
        when(statsService.getNodeCount()).thenReturn(nodeCountDto);

        ResponseEntity<NodeCountDto> response = ethereumStatsController.getNodeCount();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nodeCountDto, response.getBody());
    }

    @Test
    public void testGetNodeCount_NotFound() {
        when(statsService.getNodeCount()).thenReturn(null);

        ResponseEntity<NodeCountDto> response = ethereumStatsController.getNodeCount();

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}