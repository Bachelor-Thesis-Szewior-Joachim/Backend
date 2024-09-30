package org.example.backend.tokens.nft.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class NftToken {
    @Id
    private Long id;
    private Long rank;
    private String name;
    private double price;
    private double chain;
    private Long volume24h;
    private Long estimatedMarketCap;
    private double floorPrice;
    private double averagePrice24h;
    private Long sales24h;
    private Long assets;
    private double owners;
    private double ownersPercentage;
    @ElementCollection
    private Map<String, Long> balances = new HashMap<>();
    public NftToken() {

    }
}
