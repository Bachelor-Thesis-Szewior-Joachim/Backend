package org.example.backend.blockchain.token.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
public class Token {
    @Id
    private Long id;
    private String name;
    private String symbol;
    private int decimals;
    private long totalSupply;
    private Map<String, Long> balances = new HashMap<>();
    private Map<String, Map<String, Long>> allowances = new HashMap<>();

    public Token() {

    }
}
