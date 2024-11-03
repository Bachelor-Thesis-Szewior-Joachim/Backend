package org.example.backend.nft.entity.collection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    private String name;

    @Column(length = 1000)
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

    @ElementCollection
    @Column(name = "contract_address")
    private List<String> contracts;

    @Column(length = 100)
    private String requiredZone;

    private int totalSupply;

    @Column(length = 100)
    private String rarityStrategyVersion;

    @Column(length = 50)
    private String createdDate;
}


