package org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class HistoricalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cmcId;
    private Double price;
    private String volume24h;
    private String circulatingSupply;
    @Column(name = "date")
    private String date;
    private String marketCap;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cryptocurrency cryptocurrency;

    public HistoricalData() {

    }
}