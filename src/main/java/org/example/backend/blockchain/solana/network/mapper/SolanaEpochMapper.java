package org.example.backend.blockchain.solana.network.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.network.entity.epoch.Epoch;
import org.example.backend.blockchain.solana.network.entity.epoch.EpochDto;

public class SolanaEpochMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EpochDto toDto(Epoch epoch) {
        return EpochDto.builder()
                .absoluteSlot(epoch.getAbsoluteSlot())
                .blockHeight(epoch.getBlockHeight())
                .epoch(epoch.getEpoch())
                .slotIndex(epoch.getSlotIndex())
                .slotsInEpoch(epoch.getSlotsInEpoch())
                .transactionCount(epoch.getTransactionCount())
                .build();
    }

    public static Epoch toEntity(EpochDto epochDto) {
        return Epoch.builder()
                .absoluteSlot(epochDto.getAbsoluteSlot())
                .blockHeight(epochDto.getBlockHeight())
                .epoch(epochDto.getEpoch())
                .slotIndex(epochDto.getSlotIndex())
                .slotsInEpoch(epochDto.getSlotsInEpoch())
                .transactionCount(epochDto.getTransactionCount())
                .build();
    }

    public static EpochDto mapJsonToEpochDto(String jsonResponse) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode.path("result");
            Epoch epoch = Epoch.builder()
                    .absoluteSlot(resultNode.get("absoluteSlot").asText())
                    .blockHeight(resultNode.get("blockHeight").asText())
                    .epoch(resultNode.get("epoch").asText())
                    .slotIndex(resultNode.get("slotIndex").asText())
                    .slotsInEpoch(resultNode.get("slotsInEpoch").asText())
                    .transactionCount(resultNode.get("transactionCount").asText())
                    .build();

            return toDto(epoch);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
