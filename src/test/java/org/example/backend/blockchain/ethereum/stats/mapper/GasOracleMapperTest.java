package org.example.backend.blockchain.ethereum.stats.mapper;


import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracle;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracleDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GasOracleMapperTest {

    @Test
    public void testMapGasOracleToGasOracleDto() {
        GasOracle gasOracle = GasOracle.builder()
                .lastBlock("12345")
                .safeGasPrice("10")
                .proposeGasPrice("20")
                .fastGasPrice("30")
                .suggestBaseFee("5")
                .gasUsedRatio(Collections.singletonList("0.8"))
                .build();

        GasOracleDto dto = GasOracleMapper.mapGasOracleToGasOracleDto(gasOracle);

        assertEquals(gasOracle.getLastBlock(), dto.getLastBlock());
        assertEquals(gasOracle.getSafeGasPrice(), dto.getSafeGasPrice());
        assertEquals(gasOracle.getProposeGasPrice(), dto.getProposeGasPrice());
        assertEquals(gasOracle.getFastGasPrice(), dto.getFastGasPrice());
        assertEquals(gasOracle.getSuggestBaseFee(), dto.getSuggestBaseFee());
        assertEquals(gasOracle.getGasUsedRatio(), dto.getGasUsedRatio());
    }

    @Test
    public void testMapGasOracleDtoToGasOracle() {
        GasOracleDto dto = GasOracleDto.builder()
                .lastBlock("12345")
                .safeGasPrice("10")
                .proposeGasPrice("20")
                .fastGasPrice("30")
                .suggestBaseFee("5")
                .gasUsedRatio(Collections.singletonList("0.8"))
                .build();

        GasOracle gasOracle = GasOracleMapper.mapGasOracleDtoToGasOracle(dto);

        assertEquals(dto.getLastBlock(), gasOracle.getLastBlock());
        assertEquals(dto.getSafeGasPrice(), gasOracle.getSafeGasPrice());
        assertEquals(dto.getProposeGasPrice(), gasOracle.getProposeGasPrice());
        assertEquals(dto.getFastGasPrice(), gasOracle.getFastGasPrice());
        assertEquals(dto.getSuggestBaseFee(), gasOracle.getSuggestBaseFee());
        assertEquals(dto.getGasUsedRatio(), gasOracle.getGasUsedRatio());
    }
}