package org.example.backend.nft.entity.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String bannerImageUrl;
    private String externalUrl;
    private String blockchain;
    private String owner;
    private String category;
    private boolean isDisabled;
    private boolean traitOffersEnabled;
    private boolean collectionOffersEnabled;
    private String openseaUrl;
    private String projectUrl;
    private String wikiUrl;
    private String discordUrl;
    private String telegramUrl;
    private String twitterUsername;
    private String instagramUsername;
    private List<String> contracts;
    private String requiredZone;
    private int totalSupply;
    private String rarityStrategyVersion;
    private String createdDate;
}

