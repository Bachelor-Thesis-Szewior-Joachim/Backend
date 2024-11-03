package org.example.backend.nft.mapper.nft;


import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.owner.NFTOwnerDto;

public class NFTOwnerMapper {

    public static NFTOwner toEntity(NFTOwnerDto dto) {
        return NFTOwner.builder()
                .id(dto.getId())
                .address(dto.getAddress())
                .quantity(dto.getQuantity())
                .build();
    }

    public static NFTOwnerDto toDto(NFTOwner entity) {
        return NFTOwnerDto.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .quantity(entity.getQuantity())
                .build();
    }
}

