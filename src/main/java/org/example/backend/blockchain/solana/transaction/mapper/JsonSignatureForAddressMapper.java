package org.example.backend.blockchain.solana.transaction.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress.SignatureForAddress;
import org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress.SignatureForAddressDto;

import java.util.ArrayList;
import java.util.List;

public class JsonSignatureForAddressMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static SignatureForAddressDto toDto(SignatureForAddress entity) {
        if (entity == null) {
            return null;
        }

        SignatureForAddressDto dto = new SignatureForAddressDto();
        dto.setBlockTime(entity.getBlockTime() != null ? entity.getBlockTime().toString() : null);
        dto.setConfirmationStatus(entity.getConfirmationStatus());
        dto.setErr(entity.getErr());
        dto.setMemo(entity.getMemo());
        dto.setSignature(entity.getSignature());
        dto.setSlot(entity.getSlot() != null ? entity.getSlot().toString() : null);
        return dto;
    }

    public static SignatureForAddress toEntity(SignatureForAddressDto dto) {
        if (dto == null) {
            return null;
        }

        SignatureForAddress entity = new SignatureForAddress();
        entity.setBlockTime(String.valueOf(dto.getBlockTime() != null ? Long.parseLong(dto.getBlockTime()) : null));
        entity.setConfirmationStatus(dto.getConfirmationStatus());
        entity.setErr(dto.getErr());
        entity.setMemo(dto.getMemo());
        entity.setSignature(dto.getSignature());
        entity.setSlot(String.valueOf(dto.getSlot() != null ? Long.parseLong(dto.getSlot()) : null));
        return entity;
    }

    public static List<SignatureForAddressDto> mapJsonToSignaturesForAddress(String jsonResponse) {
        List<SignatureForAddressDto> signatureForAddresses = null;
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode.path("result");

            signatureForAddresses = new ArrayList<>();
            for (JsonNode node : resultNode) {
                SignatureForAddress signatureForAddress = objectMapper.treeToValue(node, SignatureForAddress.class);
                signatureForAddresses.add(toDto(signatureForAddress));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signatureForAddresses;
    }
}
