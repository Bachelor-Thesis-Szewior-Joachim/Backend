package org.example.backend.blockchain.bitcoin.transaction.mapper;


import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutput;
import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutputDto;

public class BitcoinTransactionOutputMapper {

    // Convert from entity to DTO
    public static BitcoinTransactionOutputDto mapBitcoinTransactionOutputToBitcoinTransactionOutputDto(BitcoinTransactionOutput output) {
        return BitcoinTransactionOutputDto.builder()
                .value(output.getValue())
                .script(output.getScript())
                .addresses(output.getAddresses())
                .scriptType(output.getScriptType())
                .dataHex(output.getDataHex())
                .build();
    }

    // Convert from DTO to entity
    public static BitcoinTransactionOutput mapBitcoinTransactionOutputDtoToBitcoinTransactionOutput(BitcoinTransactionOutputDto dto) {
        return BitcoinTransactionOutput.builder()
        .value(dto.getValue())
        .script(dto.getScript())
        .addresses(dto.getAddresses())
        .scriptType(dto.getScriptType())
        .dataHex(dto.getDataHex())
                .build();
    }
}
