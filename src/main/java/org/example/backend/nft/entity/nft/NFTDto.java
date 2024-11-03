package org.example.backend.nft.entity.nft;

import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NFTDto {
    private Long id;
    private String animationUrl;
    private String contract;
    private String creator;
    private String description;
    private String displayAnimationUrl;
    private String displayImageUrl;
    private String identifier;
    private String imageUrl;
    private Boolean isDisabled;
    private Boolean isNsfw;
    private Boolean isSuspicious;
    private String metadataUrl;
    private String name;
    private String openseaUrl;
    private String updatedAt;
    private Integer maxRank;
    private Integer rank;
    private Double score;
    private String strategyVersion;
    private Integer totalSupply;
    private Integer uniqueAttributeCount;
    private String tokenStandard;
    private Long collectionId;
}

