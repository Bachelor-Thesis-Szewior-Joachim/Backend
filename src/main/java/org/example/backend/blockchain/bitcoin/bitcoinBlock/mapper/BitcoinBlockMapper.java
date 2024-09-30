package org.example.backend.blockchain.bitcoin.bitcoinBlock.mapper;

import org.example.backend.blockchain.bitcoin.bitcoinBlock.entity.BitcoinBlock;
import org.example.backend.blockchain.bitcoin.bitcoinBlock.entity.BitcoinBlockDto;

public class BitcoinBlockMapper {

    public static BitcoinBlock mapBlockDtoToBlock(BitcoinBlockDto blockDto) {

        return BitcoinBlock.builder()
                .id(blockDto.getId())
                .hash(blockDto.getHash())
                .height(blockDto.getHeight())
                .chain(blockDto.getChain())
                .total(blockDto.getTotal())
                .fees(blockDto.getFees())
                .size(blockDto.getSize())
                .vsize(blockDto.getVsize())
                .ver(blockDto.getVer())
                .time(blockDto.getTime())
                .received_time(blockDto.getReceived_time())
                .relayed_by(blockDto.getRelayed_by())
                .bits(blockDto.getBits())
                .nonce(blockDto.getNonce())
                .n_tx(blockDto.getN_tx())
                .previousBlock(blockDto.getPreviousBlock())
                .merkle_root(blockDto.getMerkle_root())
                .transactionIds(blockDto.getTransactionIds())
                .prev_block_url(blockDto.getPrev_block_url())
                .tx_url(blockDto.getTx_url())
                .next_txids(blockDto.getNext_txids())
                .build();
    }

    public static BitcoinBlockDto mapBlockToBlockDto(BitcoinBlock block) {

        return BitcoinBlockDto.builder()
                .id(block.getId())
                .hash(block.getHash())
                .height(block.getHeight())
                .chain(block.getChain())
                .total(block.getTotal())
                .fees(block.getFees())
                .size(block.getSize())
                .vsize(block.getVsize())
                .ver(block.getVer())
                .time(block.getTime())
                .received_time(block.getReceived_time())
                .relayed_by(block.getRelayed_by())
                .bits(block.getBits())
                .nonce(block.getNonce())
                .n_tx(block.getN_tx())
                .previousBlock(block.getPreviousBlock())
                .merkle_root(block.getMerkle_root())
                .transactionIds(block.getTransactionIds())
                .prev_block_url(block.getPrev_block_url())
                .tx_url(block.getTx_url())
                .next_txids(block.getNext_txids())
                .build();
    }
}
