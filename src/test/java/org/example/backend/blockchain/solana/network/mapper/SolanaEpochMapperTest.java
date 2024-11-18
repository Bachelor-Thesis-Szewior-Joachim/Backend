package org.example.backend.blockchain.solana.network.mapper;

import org.example.backend.blockchain.solana.network.entity.epoch.Epoch;
import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaEpochMapperTest {

    @Test
    public void testToDto() {
        Epoch epoch = Epoch.builder()
                .absoluteSlot("12345")
                .blockHeight("67890")
                .epoch("1")
                .slotIndex("2")
                .slotsInEpoch("43200")
                .transactionCount("100")
                .build();

        EpochDto epochDto = SolanaEpochMapper.toDto(epoch);

        assertEquals(epoch.getAbsoluteSlot(), epochDto.getAbsoluteSlot());
        assertEquals(epoch.getBlockHeight(), epochDto.getBlockHeight());
        assertEquals(epoch.getEpoch(), epochDto.getEpoch());
        assertEquals(epoch.getSlotIndex(), epochDto.getSlotIndex());
        assertEquals(epoch.getSlotsInEpoch(), epochDto.getSlotsInEpoch());
        assertEquals(epoch.getTransactionCount(), epochDto.getTransactionCount());
    }

    @Test
    public void testToEntity() {
        EpochDto epochDto = EpochDto.builder()
                .absoluteSlot("12345")
                .blockHeight("67890")
                .epoch("1")
                .slotIndex("2")
                .slotsInEpoch("43200")
                .transactionCount("100")
                .build();

        Epoch epoch = SolanaEpochMapper.toEntity(epochDto);

        assertEquals(epochDto.getAbsoluteSlot(), epoch.getAbsoluteSlot());
        assertEquals(epochDto.getBlockHeight(), epoch.getBlockHeight());
        assertEquals(epochDto.getEpoch(), epoch.getEpoch());
        assertEquals(epochDto.getSlotIndex(), epoch.getSlotIndex());
        assertEquals(epochDto.getSlotsInEpoch(), epoch.getSlotsInEpoch());
        assertEquals(epochDto.getTransactionCount(), epoch.getTransactionCount());
    }

    @Test
    public void testMapJsonToEpochDto() throws Exception {
        String jsonResponse = "{ \"result\": { \"absoluteSlot\": \"12345\", \"blockHeight\": \"67890\", \"epoch\": \"1\", \"slotIndex\": \"2\", \"slotsInEpoch\": \"43200\", \"transactionCount\": \"100\" } }";

        EpochDto epochDto = SolanaEpochMapper.mapJsonToEpochDto(jsonResponse);

        assertEquals("12345", epochDto.getAbsoluteSlot());
        assertEquals("67890", epochDto.getBlockHeight());
        assertEquals("1", epochDto.getEpoch());
        assertEquals("2", epochDto.getSlotIndex());
        assertEquals("43200", epochDto.getSlotsInEpoch());
        assertEquals("100", epochDto.getTransactionCount());
    }
}