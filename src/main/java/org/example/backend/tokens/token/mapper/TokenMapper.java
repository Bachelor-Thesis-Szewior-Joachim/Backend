package org.example.backend.tokens.token.mapper;

import org.example.backend.tokens.token.entity.Token;
import org.example.backend.tokens.token.entity.TokenDto;

public class TokenMapper {
    public static Token mapTokenDtoToToken(TokenDto tokenDto) {
        return Token.builder()
                .id(tokenDto.getId())
                .rank(tokenDto.getRank())
                .name(tokenDto.getName())
                .price(tokenDto.getPrice())
                .change(tokenDto.getChange())
                .volume(tokenDto.getVolume())
                .circulatingMarketCap(tokenDto.getCirculatingMarketCap())
                .onChainMarketCap(tokenDto.getOnChainMarketCap())
                .holder(tokenDto.getHolder())
                .build();
    }

    public static TokenDto mapTokenToTokenDto(Token token) {
        return TokenDto.builder()
                .id(token.getId())
                .rank(token.getRank())
                .name(token.getName())
                .price(token.getPrice())
                .change(token.getChange())
                .volume(token.getVolume())
                .circulatingMarketCap(token.getCirculatingMarketCap())
                .onChainMarketCap(token.getOnChainMarketCap())
                .holder(token.getHolder())
                .build();
    }
}
