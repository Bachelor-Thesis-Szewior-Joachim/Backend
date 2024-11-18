package org.example.backend.blockchain.solana.transaction.mapper;

import org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress.SignatureForAddress;
import org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress.SignatureForAddressDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonSignatureForAddressMapperTest {

    @Test
    public void testToDto() {
        SignatureForAddress entity = new SignatureForAddress();
        entity.setBlockTime("1234567890");
        entity.setConfirmationStatus("confirmed");
        entity.setErr(null);
        entity.setMemo("memo");
        entity.setSignature("signature");
        entity.setSlot("12345");

        SignatureForAddressDto dto = JsonSignatureForAddressMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getBlockTime(), dto.getBlockTime());
        assertEquals(entity.getConfirmationStatus(), dto.getConfirmationStatus());
        assertEquals(entity.getErr(), dto.getErr());
        assertEquals(entity.getMemo(), dto.getMemo());
        assertEquals(entity.getSignature(), dto.getSignature());
        assertEquals(entity.getSlot(), dto.getSlot());
    }

    @Test
    public void testToEntity() {
        SignatureForAddressDto dto = new SignatureForAddressDto();
        dto.setBlockTime("1234567890");
        dto.setConfirmationStatus("confirmed");
        dto.setErr(null);
        dto.setMemo("memo");
        dto.setSignature("signature");
        dto.setSlot("12345");

        SignatureForAddress entity = JsonSignatureForAddressMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getBlockTime(), entity.getBlockTime());
        assertEquals(dto.getConfirmationStatus(), entity.getConfirmationStatus());
        assertEquals(dto.getErr(), entity.getErr());
        assertEquals(dto.getMemo(), entity.getMemo());
        assertEquals(dto.getSignature(), entity.getSignature());
        assertEquals(dto.getSlot(), entity.getSlot());
    }

    @Test
    public void testMapJsonToSignaturesForAddress() throws Exception {
        String jsonResponse = "{ \"result\": [ { \"blockTime\": \"1234567890\", \"confirmationStatus\": \"confirmed\", \"err\": null, \"memo\": \"memo\", \"signature\": \"signature\", \"slot\": \"12345\" } ] }";

        List<SignatureForAddressDto> dtos = JsonSignatureForAddressMapper.mapJsonToSignaturesForAddress(jsonResponse);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        SignatureForAddressDto dto = dtos.get(0);

        assertEquals("1234567890", dto.getBlockTime());
        assertEquals("confirmed", dto.getConfirmationStatus());
        assertEquals(null, dto.getErr());
        assertEquals("memo", dto.getMemo());
        assertEquals("signature", dto.getSignature());
        assertEquals("12345", dto.getSlot());
    }
}