package org.example.backend.blockchain.bitcoin.transaction.mapper;


import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;

import java.util.stream.Collectors;

public class BitcoinTransactionMapper {

    public static BitcoinTransactionDto mapTransactionToTransactionDto(BitcoinTransaction bitcoinTransaction) {
        return BitcoinTransactionDto.builder()
                .hash(bitcoinTransaction.getHash())
                .blockHeight(bitcoinTransaction.getBlockHeight())
                .blockHash(bitcoinTransaction.getBlockHash())
                .blockIndex(bitcoinTransaction.getBlockIndex())
                .total(bitcoinTransaction.getTotal())
                .fees(bitcoinTransaction.getFees())
                .size(bitcoinTransaction.getSize())
                .vsize(bitcoinTransaction.getVsize())
                .preference(bitcoinTransaction.getPreference())
                .confirmed(bitcoinTransaction.getConfirmed())
                .received(bitcoinTransaction.getReceived())
                .ver(bitcoinTransaction.getVer())
                .doubleSpend(bitcoinTransaction.isDoubleSpend())
                .vinSz(bitcoinTransaction.getVinSz())
                .voutSz(bitcoinTransaction.getVoutSz())
                .dataProtocol(bitcoinTransaction.getDataProtocol())
                .confirmations(bitcoinTransaction.getConfirmations())
                .confidence(bitcoinTransaction.getConfidence())
                .bitcoinTransactionInputsDto(
                        bitcoinTransaction.getBitcoinTransactionInputs().stream()
                                .map(input -> BitcoinTransactionInputMapper
                                        .mapFromBitcoinTransactionInputToBitcoinTransactionInputDto(input))
                                .collect(Collectors.toList()))
                .bitcoinTransactionOutputsDto(
                        bitcoinTransaction.getBitcoinTransactionOutputs().stream()
                                .map(output -> BitcoinTransactionOutputMapper
                                        .mapBitcoinTransactionOutputToBitcoinTransactionOutputDto(output))
                                .collect(Collectors.toList()))
                .addresses(bitcoinTransaction.getAddresses()) // Mapping addresses (List<List<String>>)
                .build();
    }

    public static BitcoinTransaction mapTransactionDtoToTransaction(BitcoinTransactionDto bitcoinTransactionDto) {
        return BitcoinTransaction.builder()
                .hash(bitcoinTransactionDto.getHash())
                .blockHeight(bitcoinTransactionDto.getBlockHeight())
                .blockHash(bitcoinTransactionDto.getBlockHash())
                .blockIndex(bitcoinTransactionDto.getBlockIndex())
                .total(bitcoinTransactionDto.getTotal())
                .fees(bitcoinTransactionDto.getFees())
                .size(bitcoinTransactionDto.getSize())
                .vsize(bitcoinTransactionDto.getVsize())
                .preference(bitcoinTransactionDto.getPreference())
                .confirmed(bitcoinTransactionDto.getConfirmed())
                .received(bitcoinTransactionDto.getReceived())
                .ver(bitcoinTransactionDto.getVer())
                .doubleSpend(bitcoinTransactionDto.isDoubleSpend())
                .vinSz(bitcoinTransactionDto.getVinSz())
                .voutSz(bitcoinTransactionDto.getVoutSz())
                .dataProtocol(bitcoinTransactionDto.getDataProtocol())
                .confirmations(bitcoinTransactionDto.getConfirmations())
                .confidence(bitcoinTransactionDto.getConfidence())
                .bitcoinTransactionInputs(
                        bitcoinTransactionDto.getBitcoinTransactionInputsDto().stream()
                                .map(input -> BitcoinTransactionInputMapper
                                        .mapFromBitcoinTransactionInputDtoToBitcoinTransactionInput(input))
                                .collect(Collectors.toList()))
                .bitcoinTransactionOutputs(
                        bitcoinTransactionDto.getBitcoinTransactionOutputsDto().stream()
                                .map(output -> BitcoinTransactionOutputMapper
                                        .mapBitcoinTransactionOutputDtoToBitcoinTransactionOutput(output))
                                .collect(Collectors.toList()))
                .addresses(bitcoinTransactionDto.getAddresses()) // Mapping addresses (List<List<String>>)
                .build();
    }
}
