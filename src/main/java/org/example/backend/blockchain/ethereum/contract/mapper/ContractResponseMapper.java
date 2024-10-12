package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponse;
import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponseDto;

import java.util.stream.Collectors;


public class ContractResponseMapper {

    public static ContractResponseDto mapContractResponseDtoToContractResponse(ContractResponse contractResponse) {
        return ContractResponseDto.builder()
                .status(contractResponse.getStatus())
                .message(contractResponse.getMessage())
                .result(contractResponse.getResult()
                        .stream()
                        .map(contract -> ContractMapper.mapContractToContractDto(contract))
                        .collect(Collectors.toList()))
                .build();
    }

    public static ContractResponse mapContractResponseToContractResponseDto(ContractResponseDto contractResponseDto) {
        return ContractResponse.builder()
                .status(contractResponseDto.getStatus())
                .message(contractResponseDto.getMessage())
                .result(contractResponseDto.getResult()
                        .stream()
                        .map(contract -> ContractMapper.mapContractDtoToContract(contract))
                        .collect(Collectors.toList()))
                .build();
    }
}
