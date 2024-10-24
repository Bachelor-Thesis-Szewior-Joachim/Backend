package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.CryptocurrencyDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CryptocurrencyMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Cryptocurrency mapCryptocurrencyDtoToCryptocurrency(CryptocurrencyDto cryptocurrencyDto) {
        return Cryptocurrency.builder()
                .id(cryptocurrencyDto.getId())
                .cmcId(cryptocurrencyDto.getCmcId())
                .name(cryptocurrencyDto.getName())
                .cmcRank(cryptocurrencyDto.getCmcRank())
                .symbol(cryptocurrencyDto.getSymbol())
                .circulatingSupply(cryptocurrencyDto.getCirculatingSupply())
                .price(cryptocurrencyDto.getPrice())
                .volume24h(cryptocurrencyDto.getVolume24h())
                .percentChange1h(cryptocurrencyDto.getPercentChange1h())
                .percentChange24h(cryptocurrencyDto.getPercentChange24h())
                .percentChange7d(cryptocurrencyDto.getPercentChange7d())
                .marketCap(cryptocurrencyDto.getMarketCap())
                .pricesAllTime(cryptocurrencyDto.getPricesAllTime()
                        .stream()
                        .map(historicalDataDto -> HistoricalDataMapper.fromDto(historicalDataDto))
                        .collect(Collectors.toList()))
                .build();
    }

    public static CryptocurrencyDto mapCryptocurrencyToCryptocurrencyDto(Cryptocurrency cryptocurrency) {
        return CryptocurrencyDto.builder()
                .id(cryptocurrency.getId())
                .cmcId(cryptocurrency.getCmcId())
                .name(cryptocurrency.getName())
                .cmcRank(cryptocurrency.getCmcRank())
                .symbol(cryptocurrency.getSymbol())
                .circulatingSupply(cryptocurrency.getCirculatingSupply())
                .price(cryptocurrency.getPrice())
                .volume24h(cryptocurrency.getVolume24h())
                .percentChange1h(cryptocurrency.getPercentChange1h())
                .percentChange24h(cryptocurrency.getPercentChange24h())
                .percentChange7d(cryptocurrency.getPercentChange7d())
                .marketCap(cryptocurrency.getMarketCap())
                .pricesAllTime(cryptocurrency.getPricesAllTime()
                        .stream()
                        .map(historicalData -> HistoricalDataMapper.toDto(historicalData))
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<Cryptocurrency> mapJsonResponseToCryptocurrency(String jsonResponse) {
        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.path("data");


            for (JsonNode cryptocurrencyNode : dataNode) {

                Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                        .cmcId(cryptocurrencyNode.path("id").asLong())
                        .name(cryptocurrencyNode.path("name").asText())
                        .cmcRank(cryptocurrencyNode.path("cmc_rank").asLong())
                        .symbol(cryptocurrencyNode.path("symbol").asText())
                        .circulatingSupply(cryptocurrencyNode.path("circulating_supply").asText())
                        .price(cryptocurrencyNode.path("quote").path("USD").path("price").asDouble())
                        .volume24h(cryptocurrencyNode.path("quote").path("USD").path("volume_24h").asLong())
                        .percentChange1h(cryptocurrencyNode.path("quote").path("USD").path("percent_change_1h").asDouble())
                        .percentChange24h(cryptocurrencyNode.path("quote").path("USD").path("percent_change_24h").asDouble())
                        .percentChange7d(cryptocurrencyNode.path("quote").path("USD").path("percent_change_7d").asDouble())
                        .marketCap(cryptocurrencyNode.path("quote").path("USD").path("market_cap").asLong())
                        .build();

                JsonNode platformNode = cryptocurrencyNode.path("platform");
                if (!platformNode.isNull()) {
                    Platform platform = Platform.builder()
                            .platformId(platformNode.get("id").asLong())
                            .name(platformNode.get("name").asText())
                            .symbol(platformNode.get("symbol").asText())
                            .tokenAddress(platformNode.get("token_address").asText())
                            .build();

                    cryptocurrency.setPlatform(platform);
                }
                cryptocurrencies.add(cryptocurrency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cryptocurrencies;
    }
}
