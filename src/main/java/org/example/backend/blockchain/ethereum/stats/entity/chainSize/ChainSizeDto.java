package org.example.backend.blockchain.ethereum.stats.entity.chainSize;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChainSizeDto {
    private String blockNumber;
    private String chainTimeStamp;
    private String chainSize;
    private String clientType;
    private String syncMode;

    public ChainSizeDto() {
    }
}
