package org.example.backend.blockchain.bitcoin.block.mapper;

import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlock;
import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlockDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitcoinBlockMapperTest {

    @Test
    void testMapBlockDtoToBlock() {
        BitcoinBlockDto blockDto = BitcoinBlockDto.builder()
                .id(1L)
                .hash("testHash")
                .height(1000)
                .chain("testChain")
                .total(5000L)
                .fees(100L)
                .size(200)
                .vsize(180)
                .ver(1)
                .time("2023-10-01T00:00:00Z")
                .received_time("2023-10-01T00:01:00Z")
                .relayed_by("testNode")
                .bits(123456L)
                .nonce(654321L)
                .n_tx(10)
                .previousBlock("prevHash")
                .merkle_root("merkleRoot")
                .transactionIds(List.of("tx1", "tx2"))
                .prev_block_url("prevBlockUrl")
                .tx_url("txUrl")
                .next_txids("nextTxids")
                .build();

        BitcoinBlock block = BitcoinBlockMapper.mapBlockDtoToBlock(blockDto);

        assertEquals(blockDto.getId(), block.getId());
        assertEquals(blockDto.getHash(), block.getHash());
        assertEquals(blockDto.getHeight(), block.getHeight());
        assertEquals(blockDto.getChain(), block.getChain());
        assertEquals(blockDto.getTotal(), block.getTotal());
        assertEquals(blockDto.getFees(), block.getFees());
        assertEquals(blockDto.getSize(), block.getSize());
        assertEquals(blockDto.getVsize(), block.getVsize());
        assertEquals(blockDto.getVer(), block.getVer());
        assertEquals(blockDto.getTime(), block.getTime());
        assertEquals(blockDto.getReceived_time(), block.getReceived_time());
        assertEquals(blockDto.getRelayed_by(), block.getRelayed_by());
        assertEquals(blockDto.getBits(), block.getBits());
        assertEquals(blockDto.getNonce(), block.getNonce());
        assertEquals(blockDto.getN_tx(), block.getN_tx());
        assertEquals(blockDto.getPreviousBlock(), block.getPreviousBlock());
        assertEquals(blockDto.getMerkle_root(), block.getMerkle_root());
        assertEquals(blockDto.getTransactionIds(), block.getTransactionIds());
        assertEquals(blockDto.getPrev_block_url(), block.getPrev_block_url());
        assertEquals(blockDto.getTx_url(), block.getTx_url());
        assertEquals(blockDto.getNext_txids(), block.getNext_txids());
    }

    @Test
    void testMapBlockToBlockDto() {
        BitcoinBlock block = BitcoinBlock.builder()
                .id(1L)
                .hash("testHash")
                .height(1000)
                .chain("testChain")
                .total(5000L)
                .fees(100L)
                .size(200)
                .vsize(180)
                .ver(1)
                .time("2023-10-01T00:00:00Z")
                .received_time("2023-10-01T00:01:00Z")
                .relayed_by("testNode")
                .bits(123456L)
                .nonce(654321L)
                .n_tx(10)
                .previousBlock("prevHash")
                .merkle_root("merkleRoot")
                .transactionIds(List.of("tx1", "tx2"))
                .prev_block_url("prevBlockUrl")
                .tx_url("txUrl")
                .next_txids("nextTxids")
                .build();

        BitcoinBlockDto blockDto = BitcoinBlockMapper.mapBlockToBlockDto(block);

        assertEquals(block.getId(), blockDto.getId());
        assertEquals(block.getHash(), blockDto.getHash());
        assertEquals(block.getHeight(), blockDto.getHeight());
        assertEquals(block.getChain(), blockDto.getChain());
        assertEquals(block.getTotal(), blockDto.getTotal());
        assertEquals(block.getFees(), blockDto.getFees());
        assertEquals(block.getSize(), blockDto.getSize());
        assertEquals(block.getVsize(), blockDto.getVsize());
        assertEquals(block.getVer(), blockDto.getVer());
        assertEquals(block.getTime(), blockDto.getTime());
        assertEquals(block.getReceived_time(), blockDto.getReceived_time());
        assertEquals(block.getRelayed_by(), blockDto.getRelayed_by());
        assertEquals(block.getBits(), blockDto.getBits());
        assertEquals(block.getNonce(), blockDto.getNonce());
        assertEquals(block.getN_tx(), blockDto.getN_tx());
        assertEquals(block.getPreviousBlock(), blockDto.getPreviousBlock());
        assertEquals(block.getMerkle_root(), blockDto.getMerkle_root());
        assertEquals(block.getTransactionIds(), blockDto.getTransactionIds());
        assertEquals(block.getPrev_block_url(), blockDto.getPrev_block_url());
        assertEquals(block.getTx_url(), blockDto.getTx_url());
        assertEquals(block.getNext_txids(), blockDto.getNext_txids());
    }
}