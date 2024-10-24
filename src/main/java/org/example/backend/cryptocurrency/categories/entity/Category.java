package org.example.backend.cryptocurrency.categories.entity;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Category {

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
