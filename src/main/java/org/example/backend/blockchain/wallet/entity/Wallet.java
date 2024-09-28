package org.example.backend.blockchain.wallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Wallet {
    @Id
    private Long id;

    public Wallet() {

    }
}
