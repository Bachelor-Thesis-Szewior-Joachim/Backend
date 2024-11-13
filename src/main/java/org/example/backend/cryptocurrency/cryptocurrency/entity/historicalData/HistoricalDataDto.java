package org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HistoricalDataDto {
    private Long id;
    private Long cmcId;
    private Double price;
    private String volume24h;
    private String date;
    private String marketCap;
    private String circulatingSupply;
    private Long cryptocurrencyId;
}
