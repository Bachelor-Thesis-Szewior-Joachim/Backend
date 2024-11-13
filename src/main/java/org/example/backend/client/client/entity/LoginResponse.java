package org.example.backend.client.client.entity;

import lombok.Builder;
import org.example.backend.config.TokenType;

@Builder
public record LoginResponse(
        String accessToken,
        TokenType tokenType
) {
}
