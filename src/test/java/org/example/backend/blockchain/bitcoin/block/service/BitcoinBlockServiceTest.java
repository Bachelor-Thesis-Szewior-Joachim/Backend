package org.example.backend.blockchain.bitcoin.block.service;

import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlock;
import org.example.backend.blockchain.bitcoin.block.entity.BitcoinBlockDto;
import org.example.backend.blockchain.bitcoin.block.mapper.BitcoinBlockMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BitcoinBlockServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BitcoinBlockService bitcoinBlockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBitcoinBlockData_Found() {
        String blockHash = "testBlockHash";
        BitcoinBlock bitcoinBlock = BitcoinBlock.builder()
                .id(1L)
                .hash(blockHash)
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
        BitcoinBlockDto bitcoinBlockDto = BitcoinBlockMapper.mapBlockToBlockDto(bitcoinBlock);

        when(restTemplate.getForObject(anyString(), eq(BitcoinBlock.class))).thenReturn(bitcoinBlock);

        BitcoinBlockDto result = bitcoinBlockService.getBitcoinBlockData(blockHash);

        // Asserting each field individually
        assertEquals(bitcoinBlockDto.getId(), result.getId());
        assertEquals(bitcoinBlockDto.getHash(), result.getHash());
        assertEquals(bitcoinBlockDto.getHeight(), result.getHeight());
        assertEquals(bitcoinBlockDto.getChain(), result.getChain());
        assertEquals(bitcoinBlockDto.getTotal(), result.getTotal());
        assertEquals(bitcoinBlockDto.getFees(), result.getFees());
        assertEquals(bitcoinBlockDto.getSize(), result.getSize());
        assertEquals(bitcoinBlockDto.getVsize(), result.getVsize());
        assertEquals(bitcoinBlockDto.getVer(), result.getVer());
        assertEquals(bitcoinBlockDto.getTime(), result.getTime());
        assertEquals(bitcoinBlockDto.getReceived_time(), result.getReceived_time());
        assertEquals(bitcoinBlockDto.getRelayed_by(), result.getRelayed_by());
        assertEquals(bitcoinBlockDto.getBits(), result.getBits());
        assertEquals(bitcoinBlockDto.getNonce(), result.getNonce());
        assertEquals(bitcoinBlockDto.getN_tx(), result.getN_tx());
        assertEquals(bitcoinBlockDto.getPreviousBlock(), result.getPreviousBlock());
        assertEquals(bitcoinBlockDto.getMerkle_root(), result.getMerkle_root());
        assertEquals(bitcoinBlockDto.getTransactionIds(), result.getTransactionIds());
        assertEquals(bitcoinBlockDto.getPrev_block_url(), result.getPrev_block_url());
        assertEquals(bitcoinBlockDto.getTx_url(), result.getTx_url());
        assertEquals(bitcoinBlockDto.getNext_txids(), result.getNext_txids());

        verify(restTemplate, times(1)).getForObject(anyString(), eq(BitcoinBlock.class));
    }


    @Test
    void testGetBitcoinBlockData_NotFound() {
        String blockHash = "testBlockHash";

        when(restTemplate.getForObject(anyString(), eq(BitcoinBlock.class))).thenReturn(null);

        BitcoinBlockDto result = bitcoinBlockService.getBitcoinBlockData(blockHash);

        assertNull(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(BitcoinBlock.class));
    }
}