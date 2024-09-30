package org.example.backend.tokens.token.entity;

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
public class Token {
    @Id
    private Long id;
    private Long rank;
    private String name;
    private double price;
    private double change;
    private long volume;
    private Long circulatingMarketCap;
    private Long onChainMarketCap;
    private double holder;
    @ElementCollection
    private Map<String, Long> balances = new HashMap<>();
    public Token() {

    }
}
