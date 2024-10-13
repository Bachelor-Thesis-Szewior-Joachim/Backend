package org.example.backend.blockchain.ethereum.stats.mapper;

import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracle;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracleDto;

public class GasOracleMapper {

    public static GasOracleDto mapGasOracleToGasOracleDto(GasOracle gasOracle) {
        return GasOracleDto.builder()
                .lastBlock(gasOracle.getLastBlock())
                .safeGasPrice(gasOracle.getSafeGasPrice())
                .proposeGasPrice(gasOracle.getProposeGasPrice())
                .fastGasPrice(gasOracle.getFastGasPrice())
                .suggestBaseFee(gasOracle.getSuggestBaseFee())
                .gasUsedRatio(gasOracle.getGasUsedRatio())
                .build();
    }

    public static GasOracle mapGasOracleDtoToGasOracle(GasOracleDto gasOracleDto) {
        return GasOracle.builder()
                .lastBlock(gasOracleDto.getLastBlock())
                .safeGasPrice(gasOracleDto.getSafeGasPrice())
                .proposeGasPrice(gasOracleDto.getProposeGasPrice())
                .fastGasPrice(gasOracleDto.getFastGasPrice())
                .suggestBaseFee(gasOracleDto.getSuggestBaseFee())
                .gasUsedRatio(gasOracleDto.getGasUsedRatio())
                .build();
    }
}
