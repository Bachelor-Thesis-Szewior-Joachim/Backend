package org.example.backend.blockchain.ethereum.transaction.controller;

import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.example.backend.blockchain.ethereum.transaction.service.EthereumTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class EthereumTransactionControllerTest {

    @Mock
    private EthereumTransactionService ethereumTransactionService;

    @InjectMocks
    private EthereumTransactionController ethereumTransactionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEthereumTransaction() {
        String address = "0x123";
        EthereumTransactionDto transactionDto = new EthereumTransactionDto();
        when(ethereumTransactionService.getTransactionDetails(address)).thenReturn(Optional.of(transactionDto));

        EthereumTransactionDto result = ethereumTransactionController.getEthereumTransaction(address);

        assertNotNull(result);
        assertEquals(transactionDto, result);
    }

    @Test
    public void testGetNormalTransactions() {
        String address = "0x123";
        List<EthereumTransactionDto> transactionList = List.of(new EthereumTransactionDto());
        when(ethereumTransactionService.getNormalTransactions(address, 0, 99999999, "asc")).thenReturn(Optional.of(transactionList));

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumTransactionController.getNormalTransactions(address, 0, 99999999, "asc");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionList, response.getBody());
    }

    @Test
    public void testGetNormalTransactions_NotFound() {
        String address = "0x123";
        when(ethereumTransactionService.getNormalTransactions(address, 0, 99999999, "asc")).thenReturn(Optional.empty());

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumTransactionController.getNormalTransactions(address, 0, 99999999, "asc");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetInternalTransactions() {
        String address = "0x123";
        List<EthereumTransactionDto> transactionList = List.of(new EthereumTransactionDto());
        when(ethereumTransactionService.getInternalTransactions(address, 0, 99999999, "asc")).thenReturn(Optional.of(transactionList));

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumTransactionController.getInternalTransactions(address, 0, 99999999, "asc");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionList, response.getBody());
    }

    @Test
    public void testGetInternalTransactions_NotFound() {
        String address = "0x123";
        when(ethereumTransactionService.getInternalTransactions(address, 0, 99999999, "asc")).thenReturn(Optional.empty());

        ResponseEntity<List<EthereumTransactionDto>> response = ethereumTransactionController.getInternalTransactions(address, 0, 99999999, "asc");

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testEstimateGas() {
        String to = "0x456";
        String value = "1000";
        String data = "0x789";
        String estimateGas = "21000";
        when(ethereumTransactionService.estimateGas(to, value, data)).thenReturn(Optional.of(estimateGas));

        ResponseEntity<String> response = ethereumTransactionController.estimateGas(to, value, data);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estimateGas, response.getBody());
    }

    @Test
    public void testEstimateGas_NotFound() {
        String to = "0x456";
        String value = "1000";
        String data = "0x789";
        when(ethereumTransactionService.estimateGas(to, value, data)).thenReturn(Optional.empty());

        ResponseEntity<String> response = ethereumTransactionController.estimateGas(to, value, data);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetContractExecutionStatus() {
        String txHash = "0xabc";
        Map<String, String> statusMap = Map.of("status", "success");
        when(ethereumTransactionService.getContractExecutionStatus(txHash)).thenReturn(Optional.of(statusMap));

        ResponseEntity<Map<String, String>> response = ethereumTransactionController.getContractExecutionStatus(txHash);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statusMap, response.getBody());
    }

    @Test
    public void testGetContractExecutionStatus_NotFound() {
        String txHash = "0xabc";
        when(ethereumTransactionService.getContractExecutionStatus(txHash)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, String>> response = ethereumTransactionController.getContractExecutionStatus(txHash);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetTransactionReceiptStatus() {
        String txHash = "0xdef";
        Map<String, String> receiptMap = Map.of("status", "success");
        when(ethereumTransactionService.getTransactionReceiptStatus(txHash)).thenReturn(Optional.of(receiptMap));

        ResponseEntity<Map<String, String>> response = ethereumTransactionController.getTransactionReceiptStatus(txHash);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(receiptMap, response.getBody());
    }

    @Test
    public void testGetTransactionReceiptStatus_NotFound() {
        String txHash = "0xdef";
        when(ethereumTransactionService.getTransactionReceiptStatus(txHash)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, String>> response = ethereumTransactionController.getTransactionReceiptStatus(txHash);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}