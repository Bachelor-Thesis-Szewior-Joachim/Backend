package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HistoricalDataMapperTest {

    @Test
    public void testMapJsonResponseToHistoricalData() throws Exception {
        String jsonResponse = "{ \"data\": { \"id\": 1, \"quotes\": [ { \"quote\": { \"USD\": { \"price\": 50000.0, \"volume_24h\": \"1000000000\", \"circulating_supply\": \"18000000\" } }, \"timestamp\": \"2021-01-01T00:00:00Z\" } ] } }";

        Cryptocurrency cryptocurrency = Cryptocurrency.builder().id(1L).build();

        List<HistoricalData> historicalDataList = HistoricalDataMapper.mapJsonResponseToHistoricalData(jsonResponse, cryptocurrency);

        assertNotNull(historicalDataList);
        assertEquals(1, historicalDataList.size());

        HistoricalData historicalData = historicalDataList.get(0);
        assertEquals(1L, historicalData.getId());
        assertEquals(1L, historicalData.getCmcId());
        assertEquals(50000.0, historicalData.getPrice());
        assertEquals("1000000000", historicalData.getVolume24h());
        assertEquals("18000000", historicalData.getCirculatingSupply());
        assertEquals("2021-01-01T00:00:00Z", historicalData.getDate());
        assertEquals(cryptocurrency, historicalData.getCryptocurrency());
    }

    @Test
    public void testToDto() {
        HistoricalData historicalData = HistoricalData.builder()
                .id(1L)
                .cmcId(1L)
                .price(50000.0)
                .volume24h("1000000000")
                .circulatingSupply("18000000")
                .date("2021-01-01T00:00:00Z")
                .build();

        HistoricalDataDto dto = HistoricalDataMapper.toDto(historicalData);

        assertNotNull(dto);
        assertEquals(historicalData.getId(), dto.getId());
        assertEquals(historicalData.getCmcId(), dto.getCmcId());
        assertEquals(historicalData.getPrice(), dto.getPrice());
        assertEquals(historicalData.getVolume24h(), dto.getVolume24h());
        assertEquals(historicalData.getCirculatingSupply(), dto.getCirculatingSupply());
        assertEquals(historicalData.getDate(), dto.getDate());
    }

    @Test
    public void testToEntity() {
        HistoricalDataDto dto = HistoricalDataDto.builder()
                .id(1L)
                .cmcId(1L)
                .price(50000.0)
                .volume24h("1000000000")
                .circulatingSupply("18000000")
                .date("2021-01-01T00:00:00Z")
                .build();

        Cryptocurrency cryptocurrency = Cryptocurrency.builder().id(1L).build();

        HistoricalData entity = HistoricalDataMapper.toEntity(dto, cryptocurrency);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCmcId(), entity.getCmcId());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getVolume24h(), entity.getVolume24h());
        assertEquals(dto.getCirculatingSupply(), entity.getCirculatingSupply());
        assertEquals(dto.getDate(), entity.getDate());
        assertEquals(cryptocurrency, entity.getCryptocurrency());
    }
}