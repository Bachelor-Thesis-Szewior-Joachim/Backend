package org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CryptocurrencyDto {
    private Long id;
    private Long cmcId;
    private String name;
    private Long cmcRank;
    private String symbol;
    private String circulatingSupply;
    private double price;
    private Long volume24h;
    private double percentChange1h;
    private double percentChange24h;
    private double percentChange7d;
    private Long marketCap;
    private List<HistoricalDataDto> pricesAllTime;
    private PlatformDto platformDto;
    public CryptocurrencyDto() {

    }
}
