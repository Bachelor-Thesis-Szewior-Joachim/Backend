package org.example.backend.client.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WalletDto {
    private String name;
    private String token;
    private List<String> addresses;
}
