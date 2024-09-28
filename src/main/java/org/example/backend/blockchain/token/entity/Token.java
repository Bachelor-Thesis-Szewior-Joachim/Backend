package org.example.backend.blockchain.token.entity;

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
    private String name;
    private String symbol;
    private int decimals;
    private long totalSupply;
    @ElementCollection
    private Map<String, Long> balances = new HashMap<>();
    public Token() {

    }
}
