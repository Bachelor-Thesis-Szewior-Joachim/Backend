package org.example.backend.nft.entity.nft.trait;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.nft.entity.nft.NFT;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NFTTrait {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String traitType;
    private String displayType;
    private String maxValue;
    private String value;

    @ManyToOne
    @JoinColumn(name = "nft_id")
    private NFT nft;
}
