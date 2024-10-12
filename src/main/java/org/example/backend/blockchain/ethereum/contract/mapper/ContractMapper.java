package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.contract.Contract;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;

public class ContractMapper {

    public static Contract mapContractDtoToContract(ContractDto contractDto) {
        return Contract.builder()
                .contractAddress(contractDto.getContractAddress())
                .contractCreator(contractDto.getContractCreator())
                .txHash(contractDto.getTxHash())
                .build();
    }

    public static ContractDto mapContractToContractDto(Contract contract) {
        return ContractDto.builder()
                .contractAddress(contract.getContractAddress())
                .contractCreator(contract.getContractCreator())
                .txHash(contract.getTxHash())
                .build();
    }
}
