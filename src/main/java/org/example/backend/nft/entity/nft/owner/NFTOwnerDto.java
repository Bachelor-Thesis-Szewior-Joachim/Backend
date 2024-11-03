package org.example.backend.nft.entity.nft.owner;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class NFTOwnerDto {
    private Long id;
    private String address;
    private int quantity;
}
