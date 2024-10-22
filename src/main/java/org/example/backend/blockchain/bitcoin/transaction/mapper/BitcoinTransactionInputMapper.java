package org.example.backend.blockchain.bitcoin.transaction.mapper;


import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInput;
import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;

public class BitcoinTransactionInputMapper {

    public static BitcoinTransactionInputDto mapFromBitcoinTransactionInputToBitcoinTransactionInputDto(BitcoinTransactionInput input) {
        return BitcoinTransactionInputDto.builder()
                .outputIndex(input.getOutputIndex())
                .scriptType(input.getScriptType())
                .script(input.getScript())
                .sequence(input.getSequence())
                .age(input.getAge())
                .build();
    }

    // Convert from DTO to entity
    public static BitcoinTransactionInput mapFromBitcoinTransactionInputDtoToBitcoinTransactionInput(BitcoinTransactionInputDto dto) {
        return BitcoinTransactionInput.builder()
        .outputIndex(dto.getOutputIndex())
        .scriptType(dto.getScriptType())
        .script(dto.getScript())
        .sequence(dto.getSequence())
        .age(dto.getAge())
                .build();
    }
}
