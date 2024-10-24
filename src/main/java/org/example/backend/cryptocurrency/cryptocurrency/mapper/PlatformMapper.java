package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;

public class PlatformMapper {

    public static PlatformDto toDto(Platform platform) {

        return new PlatformDto(
                platform.getId(),
                platform.getPlatformId(),
                platform.getName(),
                platform.getSymbol(),
                platform.getTokenAddress(),
                platform.getCryptocurrency() != null
                        ? CryptocurrencyMapper.mapCryptocurrencyToCryptocurrencyDto(platform.getCryptocurrency())
                        : null
        );
    }

    public static Platform toEntity(PlatformDto platformDto) {

        return Platform.builder()
                .id(platformDto.getId())
                .platformId(platformDto.getPlatformId())
                .name(platformDto.getName())
                .symbol(platformDto.getSymbol())
                .tokenAddress(platformDto.getTokenAddress())
                .cryptocurrency(platformDto.getCryptocurrencyDto() != null
                        ? CryptocurrencyMapper.mapCryptocurrencyDtoToCryptocurrency(platformDto.getCryptocurrencyDto())
                        : null)
                .build();
    }

}
