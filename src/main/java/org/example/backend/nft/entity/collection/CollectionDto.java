package org.example.backend.nft.entity.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {
    private Long id;
    private String slug;
    private String name;
    private String description;
    private String imageUrl;
    private String externalUrl;
    private String blockchain;
}
