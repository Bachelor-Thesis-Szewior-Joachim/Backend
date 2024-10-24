package org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class HistoricalData {

    @Id
    private Long id;
    private Long cmcId;
    private Double price;
    private String volume24h;
    private String circulatingSupply;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cryptocurrency cryptocurrency;
}