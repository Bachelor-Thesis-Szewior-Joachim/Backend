package org.example.backend.blockchain.bitcoin.transaction.mapper;


import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInput;
import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;

public class BitcoinTransactionInputMapper {

    public static BitcoinTransactionInputDto mapFromBitcoinTransactionInputToBitcoinTransactionInputDto(BitcoinTransactionInput input) {
        return BitcoinTransactionInputDto.builder()
                .prevHash(input.getPrevHash())
                .outputIndex(input.getOutputIndex())
                .outputValue(input.getOutputValue())
                .scriptType(input.getScriptType())
                .script(input.getScript())
                .addresses(input.getAddresses())
                .sequence(input.getSequence())
                .age(input.getAge())
                .walletName(input.getWalletName())
                .walletToken(input.getWalletToken())
                .build();
    }

    // Convert from DTO to entity
    public static BitcoinTransactionInput mapFromBitcoinTransactionInputDtoToBitcoinTransactionInput(BitcoinTransactionInputDto dto) {
        return BitcoinTransactionInput.builder()
        .prevHash(dto.getPrevHash())
        .outputIndex(dto.getOutputIndex())
        .outputValue(dto.getOutputValue())
        .scriptType(dto.getScriptType())
        .script(dto.getScript())
        .addresses(dto.getAddresses())
        .sequence(dto.getSequence())
        .age(dto.getAge())
        .walletName(dto.getWalletName())
        .walletToken(dto.getWalletToken())
                .build();
    }
}
