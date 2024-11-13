package org.example.backend.nft.entity.nft.owner;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.nft.entity.nft.NFT;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NFTOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "nft_id")
    private NFT nft;
}
