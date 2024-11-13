package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoricalDataMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;

    public static List<HistoricalData> mapJsonResponseToHistoricalData(String jsonResponse, Cryptocurrency cryptocurrency) {
        List<HistoricalData> historicalDataList = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.path("data");
            JsonNode quotesNode = dataNode.path("quotes");

            Long cmcId = dataNode.path("id").asLong();

            for (JsonNode quoteNode : quotesNode) {
                JsonNode usdQuoteNode = quoteNode.path("quote").path("USD");

                HistoricalData historicalData = HistoricalData.builder()
                        .id(cmcId) // This should be unique, consider a composite key for better handling
                        .cmcId(cmcId)
                        .price(usdQuoteNode.path("price").asDouble())
                        .volume24h(usdQuoteNode.path("volume_24h").asText())
                        .circulatingSupply(usdQuoteNode.path("circulating_supply").asText())
                        .date(quoteNode.path("timestamp").asText())
                        .cryptocurrency(cryptocurrency)
                        .build();

                historicalDataList.add(historicalData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historicalDataList;
    }

     public static HistoricalDataDto toDto(HistoricalData historicalData) {
            return HistoricalDataDto.builder()
                    .id(historicalData.getId())
                    .cmcId(historicalData.getCmcId())
                    .price(historicalData.getPrice())
                    .volume24h(historicalData.getVolume24h())
                    .circulatingSupply(historicalData.getCirculatingSupply())
                    .date(historicalData.getDate())
                    .marketCap(historicalData.getMarketCap())
                    .cryptocurrencyId(historicalData.getCryptocurrency() != null ? historicalData.getCryptocurrency().getId() : null)
                    .build();
        }

        public static HistoricalData toEntity(HistoricalDataDto dto, Cryptocurrency cryptocurrency) {
            return HistoricalData.builder()
                    .id(dto.getId())
                    .cmcId(dto.getCmcId())
                    .price(dto.getPrice())
                    .volume24h(dto.getVolume24h())
                    .circulatingSupply(dto.getCirculatingSupply())
                    .date(dto.getDate())
                    .marketCap(dto.getMarketCap())
                    .cryptocurrency(cryptocurrency)
                    .build();
        }
    }

