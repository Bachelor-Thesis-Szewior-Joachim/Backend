package org.example.backend.cryptocurrency.cryptocurrency.entity.platform;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Platform {

    @Id
    private Long id;
    private Long platformId;
    private String name;
    private String symbol;
    private String tokenAddress;
    @OneToOne
    private Cryptocurrency cryptocurrency;
}