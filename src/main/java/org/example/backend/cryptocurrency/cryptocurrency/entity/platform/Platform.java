package org.example.backend.cryptocurrency.cryptocurrency.entity.platform;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long platformId;
    private String name;
    private String symbol;
    private String tokenAddress;
    private Long cmcId;
    @OneToOne
    private Cryptocurrency cryptocurrency;
}