package org.example.backend.blockchain.bitcoin.transaction.mapper;


import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.BitcoinTransactionDto;

public class BitcoinTransactionMapper {

    public static BitcoinTransactionDto mapTransactionToTransactionDto(BitcoinTransaction bitcoinTransaction) {
        return BitcoinTransactionDto.builder()
                .hash(bitcoinTransaction.getHash())
                .block_height(bitcoinTransaction.getBlock_height())
                .total(bitcoinTransaction.getTotal())
                .fees(bitcoinTransaction.getFees())
                .size(bitcoinTransaction.getSize())
                .relayed_by(bitcoinTransaction.getRelayed_by())
                .confirmed(bitcoinTransaction.getConfirmed())
                .received(bitcoinTransaction.getReceived())
                .inputs(bitcoinTransaction.getInputs())
                .outputs(bitcoinTransaction.getOutputs())
                .build();
    }

    public static BitcoinTransaction mapTransactionDtoToTransaction(BitcoinTransactionDto transactionDto) {
        return BitcoinTransaction.builder()
                .hash(transactionDto.getHash())
                .block_height(transactionDto.getBlock_height())
                .total(transactionDto.getTotal())
                .fees(transactionDto.getFees())
                .size(transactionDto.getSize())
                .relayed_by(transactionDto.getRelayed_by())
                .confirmed(transactionDto.getConfirmed())
                .received(transactionDto.getReceived())
                .inputs(transactionDto.getInputs())
                .outputs(transactionDto.getOutputs())
                .build();
    }
}
