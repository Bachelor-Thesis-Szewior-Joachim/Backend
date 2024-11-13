package org.example.backend.blockchain.solana.network.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.network.entity.supply.Supply;
import org.example.backend.blockchain.solana.network.entity.supply.SupplyDto;


public class SolanaSupplyMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SupplyDto mapToDto(Supply supply) {
        return SupplyDto.builder()
                .circulating(supply.getCirculating())
                .notCirculating(supply.getNotCirculating())
                .total(supply.getTotal())
                .build();
    }

    public static Supply mapToEntity(SupplyDto supplyDto) {
        return Supply.builder()
                .circulating(supplyDto.getCirculating())
                .notCirculating(supplyDto.getNotCirculating())
                .total(supplyDto.getTotal())
                .build();
    }

    public static SupplyDto mapFromJsonToSupplyDto(String jsonResponse) {

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode valueNode = rootNode.path("result").path("value");

            Supply supply = Supply.builder()
                    .circulating(valueNode.get("circulating").asText())
                    .notCirculating(valueNode.get("nonCirculating").asText())
                    .total(valueNode.get("total").asText())
                    .build();
            return mapToDto(supply);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
