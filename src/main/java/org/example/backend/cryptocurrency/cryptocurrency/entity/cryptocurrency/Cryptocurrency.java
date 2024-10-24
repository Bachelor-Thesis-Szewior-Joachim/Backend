package org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.categories.entity.Category;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cryptocurrency {
    @Id
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
    @OneToMany(mappedBy = "cryptocurrency")
    private List<HistoricalData> pricesAllTime;
    @OneToOne
    private Platform platform;
    @ManyToOne
    private Category category;
    public Cryptocurrency() {

    }
}
