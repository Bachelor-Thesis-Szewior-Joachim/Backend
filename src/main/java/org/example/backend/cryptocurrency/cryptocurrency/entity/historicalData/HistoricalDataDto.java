package org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HistoricalDataDto {
    private Long id;
    private Long cmcId;
    private Double price;
    private String volume24h;
    private LocalDate date;
    private String circulatingSupply;
    private CryptocurrencyDto cryptocurrencyDto;
}
