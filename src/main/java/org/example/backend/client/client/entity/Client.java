package org.example.backend.client.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Client implements UserDetails {

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
    private Double balance;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public String getUsername() {
        return username;
    }
}
