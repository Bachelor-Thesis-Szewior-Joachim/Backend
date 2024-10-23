package org.example.backend.client.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private boolean enabled;
    @NotNull
    private String publicKey;
    @NotNull
    private String privateKey;

    public Client() {

    }

    public Client(@NotNull String username, @NotNull String password, @NotNull String publicKey, @NotNull String privateKey ) {
        this.username = username;
        this.password = password;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public Client(@NotNull String username, @NotNull String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
