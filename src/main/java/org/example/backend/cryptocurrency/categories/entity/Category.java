package org.example.backend.cryptocurrency.categories.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String categoryId;
    private String name;
    private String title;
    private String description;
    private Long numberOfTokens;
    private Double avgPriceChange;
    private String marketCap;
    private String marketCapChange;
    private String volume;
    private String volumeChange;
    @OneToMany(mappedBy = "category")
    private List<Cryptocurrency> cryptocurrencies;
    public Category() {

    }
}
