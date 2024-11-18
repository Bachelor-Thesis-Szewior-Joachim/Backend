package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CryptocurrencyMapperTest {

//    @Test
//    public void testToDto() {
//        Platform platform = Platform.builder()
//                .platformId(1L)
//                .name("Ethereum")
//                .symbol("ETH")
//                .tokenAddress("0x123")
//                .build();
//
//        Cryptocurrency cryptocurrency = Cryptocurrency.builder()
//                .id(1L)
//                .cmcId(1L)
//                .name("Bitcoin")
//                .cmcRank(1L)
//                .symbol("BTC")
//                .circulatingSupply("18000000")
//                .price(50000.0)
//                .volume24h(1000000000L)
//                .percentChange1h(0.5)
//                .percentChange24h(1.0)
//                .percentChange7d(5.0)
//                .marketCap(900000000000L)
//                .platform(platform)
//                .category("Currency")
//                .build();
//
//        List<HistoricalDataDto> historicalDataDtoList = List.of(
//                HistoricalDataDto.builder().id(1L).price(50000.0).build()
//        );
//
//        if (cryptocurrency.getId()==null) {
//            System.out.println("Cryptocurrency ID is null");
//        }
//
//        CryptocurrencyDto dto = CryptocurrencyMapper.toDto(cryptocurrency, historicalDataDtoList);
//
//        assertNotNull(dto);
//        assertEquals(cryptocurrency.getId(), dto.getId());
//        assertEquals(cryptocurrency.getCmcId(), dto.getCmcId());
//        assertEquals(cryptocurrency.getName(), dto.getName());
//        assertEquals(cryptocurrency.getCmcRank(), dto.getCmcRank());
//        assertEquals(cryptocurrency.getSymbol(), dto.getSymbol());
//        assertEquals(cryptocurrency.getCirculatingSupply(), dto.getCirculatingSupply());
//        assertEquals(cryptocurrency.getPrice(), dto.getPrice());
//        assertEquals(cryptocurrency.getVolume24h(), dto.getVolume24h());
//        assertEquals(cryptocurrency.getPercentChange1h(), dto.getPercentChange1h());
//        assertEquals(cryptocurrency.getPercentChange24h(), dto.getPercentChange24h());
//        assertEquals(cryptocurrency.getPercentChange7d(), dto.getPercentChange7d());
//        assertEquals(cryptocurrency.getMarketCap(), dto.getMarketCap());
//        assertEquals(cryptocurrency.getCategory(), dto.getCategory());
//        assertNotNull(dto.getPlatformDto());
//        assertEquals(platform.getPlatformId(), dto.getPlatformDto().getId());
//    }

    @Test
    public void testToEntity() {
        CryptocurrencyDto dto = CryptocurrencyDto.builder()
                .id(1L)
                .cmcId(1L)
                .name("Bitcoin")
                .cmcRank(1L)
                .symbol("BTC")
                .circulatingSupply("18000000")
                .price(50000.0)
                .volume24h(1000000000L)
                .percentChange1h(0.5)
                .percentChange24h(1.0)
                .percentChange7d(5.0)
                .marketCap(900000000000L)
                .category("Currency")
                .build();

        Cryptocurrency entity = CryptocurrencyMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getCmcId(), entity.getCmcId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getCmcRank(), entity.getCmcRank());
        assertEquals(dto.getSymbol(), entity.getSymbol());
        assertEquals(dto.getCirculatingSupply(), entity.getCirculatingSupply());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getVolume24h(), entity.getVolume24h());
        assertEquals(dto.getPercentChange1h(), entity.getPercentChange1h());
        assertEquals(dto.getPercentChange24h(), entity.getPercentChange24h());
        assertEquals(dto.getPercentChange7d(), entity.getPercentChange7d());
        assertEquals(dto.getMarketCap(), entity.getMarketCap());
        assertEquals(dto.getCategory(), entity.getCategory());
    }

    @Test
    public void testMapJsonResponseToCryptocurrency() throws Exception {
        String jsonResponse = "{ \"data\": [ { \"id\": 1, \"name\": \"Bitcoin\", \"cmc_rank\": 1, \"symbol\": \"BTC\", \"circulating_supply\": \"18000000\", \"quote\": { \"USD\": { \"price\": 50000.0, \"volume_24h\": 1000000000, \"percent_change_1h\": 0.5, \"percent_change_24h\": 1.0, \"percent_change_7d\": 5.0, \"market_cap\": 900000000000 } }, \"platform\": { \"id\": 1, \"name\": \"Ethereum\", \"symbol\": \"ETH\", \"token_address\": \"0x123\" } } ] }";

        List<Cryptocurrency> cryptocurrencies = CryptocurrencyMapper.mapJsonResponseToCryptocurrency(jsonResponse);

        assertNotNull(cryptocurrencies);
        assertEquals(1, cryptocurrencies.size());

        Cryptocurrency cryptocurrency = cryptocurrencies.get(0);
        assertEquals(1L, cryptocurrency.getCmcId());
        assertEquals("Bitcoin", cryptocurrency.getName());
        assertEquals(1L, cryptocurrency.getCmcRank());
        assertEquals("BTC", cryptocurrency.getSymbol());
        assertEquals("18000000", cryptocurrency.getCirculatingSupply());
        assertEquals(50000.0, cryptocurrency.getPrice());
        assertEquals(1000000000L, cryptocurrency.getVolume24h());
        assertEquals(0.5, cryptocurrency.getPercentChange1h());
        assertEquals(1.0, cryptocurrency.getPercentChange24h());
        assertEquals(5.0, cryptocurrency.getPercentChange7d());
        assertEquals(900000000000L, cryptocurrency.getMarketCap());

        Platform platform = cryptocurrency.getPlatform();
        assertNotNull(platform);
        assertEquals(1L, platform.getPlatformId());
        assertEquals("Ethereum", platform.getName());
        assertEquals("ETH", platform.getSymbol());
        assertEquals("0x123", platform.getTokenAddress());
    }

    @Test
    public void testGetCategoryFromJsonResponse() throws Exception {
        String jsonResponse = "{ \"data\": { \"1\": { \"category\": \"Currency\" } } }";

        String category = CryptocurrencyMapper.getCategoryFromJsonResponse(jsonResponse);

        assertEquals("Currency", category);
    }
}