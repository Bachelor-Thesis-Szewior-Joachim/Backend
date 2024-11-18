package org.example.backend.nft.mapper.nft;

import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.owner.NFTOwnerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NFTOwnerMapperTest {

    @Test
    public void testToDto() {
        NFTOwner entity = NFTOwner.builder()
                .id(1L)
                .address("0x123")
                .quantity(10)
                .build();

        NFTOwnerDto dto = NFTOwnerMapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getAddress(), dto.getAddress());
        assertEquals(entity.getQuantity(), dto.getQuantity());
    }

    @Test
    public void testToEntity() {
        NFTOwnerDto dto = NFTOwnerDto.builder()
                .id(1L)
                .address("0x123")
                .quantity(10)
                .build();

        NFTOwner entity = NFTOwnerMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getAddress(), entity.getAddress());
        assertEquals(dto.getQuantity(), entity.getQuantity());
    }
}