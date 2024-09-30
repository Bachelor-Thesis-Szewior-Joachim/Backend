package org.example.backend.cryptocurrency.cryptocurrency.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CryptocurrencyDto {
    private Long id;
    private Long rank;
    private String name;
    private double price;
    private double change;
    private long volume;
    private Long circulatingMarketCap;
    private Long onChainMarketCap;
    private double holder;

    public CryptocurrencyDto() {

    }
}
