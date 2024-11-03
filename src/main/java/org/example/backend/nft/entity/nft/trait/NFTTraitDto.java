package org.example.backend.nft.entity.nft.trait;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class NFTTraitDto {
    private Long id;
    private String traitType;
    private String displayType;
    private String maxValue;
    private String value;
}
