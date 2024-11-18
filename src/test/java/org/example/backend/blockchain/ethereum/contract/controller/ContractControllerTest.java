package org.example.backend.blockchain.ethereum.contract.controller;

import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCodeDto;
import org.example.backend.blockchain.ethereum.contract.service.ContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ContractControllerTest {

    @Mock
    private ContractService contractService;

    @InjectMocks
    private ContractController contractController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetContractCreationInfo() {
        List<String> contractAddresses = Arrays.asList("0x123", "0x456");
        List<ContractDto> contractDtos = Arrays.asList(new ContractDto(), new ContractDto());
        when(contractService.getContractsCreationInfo(contractAddresses)).thenReturn(contractDtos);

        ResponseEntity<List<ContractDto>> response = contractController.getContractCreationInfo(contractAddresses);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contractDtos, response.getBody());
    }

    @Test
    public void testGetSourceCode() {
        String contractAddress = "0x123";
        SourceCodeDto sourceCodeDto = new SourceCodeDto();
        when(contractService.getSourceCode(contractAddress)).thenReturn(Optional.of(sourceCodeDto));

        ResponseEntity<SourceCodeDto> response = contractController.getSourceCode(contractAddress);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sourceCodeDto, response.getBody());
    }

    @Test
    public void testGetSourceCode_NotFound() {
        String contractAddress = "0x123";
        when(contractService.getSourceCode(contractAddress)).thenReturn(Optional.empty());

        ResponseEntity<SourceCodeDto> response = contractController.getSourceCode(contractAddress);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}