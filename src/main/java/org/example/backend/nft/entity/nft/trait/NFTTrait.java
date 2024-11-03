package org.example.backend.nft.entity.nft.trait;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
