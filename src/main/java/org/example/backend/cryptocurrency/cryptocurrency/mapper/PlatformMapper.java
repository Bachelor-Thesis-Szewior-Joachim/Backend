package org.example.backend.cryptocurrency.cryptocurrency.mapper;

import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;

public class PlatformMapper {

    public static PlatformDto toDto(Platform platform) {
        if (platform == null) {
            return null;
        }
        return PlatformDto.builder()
                .id(platform.getId())
                .name(platform.getName())
                .cmcId(platform.getCmcId())
                .cryptocurrencyDto(CryptocurrencyMapper.toDtoForRanking(platform.getCryptocurrency()))
                .tokenAddress(platform.getTokenAddress())
                .symbol(platform.getSymbol())
                .build();
    }

    public static Platform toEntity(PlatformDto platformDto) {

        return Platform.builder()
                .id(platformDto.getId())
                .platformId(platformDto.getPlatformId())
                .name(platformDto.getName())
                .symbol(platformDto.getSymbol())
                .tokenAddress(platformDto.getTokenAddress())
                .build();
    }

}
