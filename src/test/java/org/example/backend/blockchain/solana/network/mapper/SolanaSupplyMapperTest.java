package org.example.backend.blockchain.solana.network.mapper;

import org.example.backend.blockchain.solana.network.entity.supply.Supply;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaSupplyMapperTest {

    @Test
    public void testMapToDto() {
        Supply supply = Supply.builder()
                .circulating("5000000")
                .notCirculating("2000000")
                .total("7000000")
                .build();

        SupplyDto supplyDto = SolanaSupplyMapper.mapToDto(supply);

        assertEquals(supply.getCirculating(), supplyDto.getCirculating());
        assertEquals(supply.getNotCirculating(), supplyDto.getNotCirculating());
        assertEquals(supply.getTotal(), supplyDto.getTotal());
    }

    @Test
    public void testMapToEntity() {
        SupplyDto supplyDto = SupplyDto.builder()
                .circulating("5000000")
                .notCirculating("2000000")
                .total("7000000")
                .build();

        Supply supply = SolanaSupplyMapper.mapToEntity(supplyDto);

        assertEquals(supplyDto.getCirculating(), supply.getCirculating());
        assertEquals(supplyDto.getNotCirculating(), supply.getNotCirculating());
        assertEquals(supplyDto.getTotal(), supply.getTotal());
    }

    @Test
    public void testMapFromJsonToSupplyDto() throws Exception {
        String jsonResponse = "{ \"result\": { \"value\": { \"circulating\": \"5000000\", \"nonCirculating\": \"2000000\", \"total\": \"7000000\" } } }";

        SupplyDto supplyDto = SolanaSupplyMapper.mapFromJsonToSupplyDto(jsonResponse);

        assertEquals("5000000", supplyDto.getCirculating());
        assertEquals("2000000", supplyDto.getNotCirculating());
        assertEquals("7000000", supplyDto.getTotal());
    }
}