package org.example.backend.client.client.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ClientDto {

    private UUID id;
    private String username;
    private String password;
    private boolean enabled;
    private String publicKey;
    private String privateKey;
    private Double balance;
}
