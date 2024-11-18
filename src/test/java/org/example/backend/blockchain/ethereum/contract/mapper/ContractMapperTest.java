package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.contract.Contract;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContractMapperTest {

    @Test
    public void testMapToContractDto() {
        Contract contract = new Contract();
        contract.setContractAddress("0x123");
        contract.setContractCreator("0xcreator");
        contract.setTxHash("0xtxhash");

        ContractDto contractDto = ContractMapper.mapContractToContractDto(contract);

        assertEquals(contract.getContractAddress(), contractDto.getContractAddress());
        assertEquals(contract.getContractCreator(), contractDto.getContractCreator());
        assertEquals(contract.getTxHash(), contractDto.getTxHash());
    }

    @Test
    public void testMapToContract() {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractAddress("0x123");
        contractDto.setContractCreator("0xcreator");
        contractDto.setTxHash("0xtxhash");

        Contract contract = ContractMapper.mapContractDtoToContract(contractDto);

        assertEquals(contractDto.getContractAddress(), contract.getContractAddress());
        assertEquals(contractDto.getContractCreator(), contract.getContractCreator());
        assertEquals(contractDto.getTxHash(), contract.getTxHash());
    }
}