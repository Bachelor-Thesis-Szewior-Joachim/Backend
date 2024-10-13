package org.example.backend.blockchain.ethereum.stats.entity.gasOracle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GasOracleDto {

    private String lastBlock;
    private String safeGasPrice;
    private String proposeGasPrice;
    private String fastGasPrice;
    private String suggestBaseFee;
    private List<String> gasUsedRatio;

    public GasOracleDto() {

    }
}