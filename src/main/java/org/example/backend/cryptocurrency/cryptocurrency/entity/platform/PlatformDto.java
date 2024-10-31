package org.example.backend.cryptocurrency.cryptocurrency.entity.platform;

import lombok.*;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformDto {

    private Long id;
    private Long platformId;
    private String name;
    private String symbol;
    private String tokenAddress;
    private CryptocurrencyDto cryptocurrencyDto;
}
