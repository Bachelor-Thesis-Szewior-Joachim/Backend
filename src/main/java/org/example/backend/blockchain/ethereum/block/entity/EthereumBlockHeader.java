package org.example.backend.blockchain.ethereum.block.entity;

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
public class EthereumBlockHeader {

    @Id
    private Long id;
    private int version;
    private String previousBlockHash;
    private String merkleRoot;
    private long timestamp;
    private int difficultyTarget;
    private int nonce;

    public EthereumBlockHeader() {

    }
}