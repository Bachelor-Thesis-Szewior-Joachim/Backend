package org.example.backend.nft.entity.nft;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.rarity.NFTRarity;
import org.example.backend.nft.entity.nft.trait.NFTTrait;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NFT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;
    private String contract;
    private String tokenStandard;
    private String name;

    @Column(length = 1000)
    private String description;

    private String imageUrl;
    private String displayImageUrl;
    private String displayAnimationUrl;
    private String metadataUrl;
    private String openseaUrl;
    private String updatedAt;
    private Boolean isDisabled;
    private Boolean isNsfw;
    private Boolean isSuspicious;
    private String animationUrl;
    private String creator;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private List<NFTTrait> traits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private List<NFTOwner> owners;

    @Embedded
    private NFTRarity rarity;
}
