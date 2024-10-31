package org.example.backend.cryptocurrency.categories.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CategoryDto {

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
    private List<CryptocurrencyDto> cryptocurrencies;

    public CategoryDto() {

    }
}
