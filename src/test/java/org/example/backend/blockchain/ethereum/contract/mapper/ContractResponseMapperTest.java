package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.contract.Contract;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponse;
import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractResponseMapperTest {

    @Test
    public void testMapContractResponseDtoToContractResponse() {
        Contract contract = new Contract();
        contract.setContractAddress("0x123");
        contract.setContractCreator("0xcreator");
        contract.setTxHash("0xtxhash");

        ContractResponse contractResponse = ContractResponse.builder()
                .status(200)
                .message("Success")
                .result(List.of(contract))
                .build();

        ContractResponseDto contractResponseDto = ContractResponseMapper.mapContractResponseDtoToContractResponse(contractResponse);

        assertEquals(contractResponse.getStatus(), contractResponseDto.getStatus());
        assertEquals(contractResponse.getMessage(), contractResponseDto.getMessage());
        assertEquals(contractResponse.getResult().size(), contractResponseDto.getResult().size());
        assertEquals(contractResponse.getResult().get(0).getContractAddress(), contractResponseDto.getResult().get(0).getContractAddress());
    }

    @Test
    public void testMapContractResponseToContractResponseDto() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractAddress("0x123");
        contractDto.setContractCreator("0xcreator");
        contractDto.setTxHash("0xtxhash");

        ContractResponseDto contractResponseDto = ContractResponseDto.builder()
                .status(200)
                .message("Success")
                .result(List.of(contractDto))
                .build();

        ContractResponse contractResponse = ContractResponseMapper.mapContractResponseToContractResponseDto(contractResponseDto);

        assertEquals(contractResponseDto.getStatus(), contractResponse.getStatus());
        assertEquals(contractResponseDto.getMessage(), contractResponse.getMessage());
        assertEquals(contractResponseDto.getResult().size(), contractResponse.getResult().size());
        assertEquals(contractResponseDto.getResult().get(0).getContractAddress(), contractResponse.getResult().get(0).getContractAddress());
    }
}