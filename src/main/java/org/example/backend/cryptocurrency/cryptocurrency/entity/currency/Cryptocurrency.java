package org.example.backend.cryptocurrency.cryptocurrency.entity.currency;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonManagedReference
    private List<HistoricalData> pricesAllTime;
    @OneToOne
    private Platform platform;
    private String category;
    public Cryptocurrency() {

    }
}
