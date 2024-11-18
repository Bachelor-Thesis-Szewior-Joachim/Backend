package org.example.backend.blockchain.ethereum.stats.mapper;

import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCount;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCountDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeCountMapperTest {

    @Test
    public void testToDto() {
        NodeCount nodeCount = new NodeCount();
        nodeCount.setUTCDate("2023-01-01T00:00:00Z");
        nodeCount.setTotalNodeCount("100");

        NodeCountDto dto = NodeCountMapper.toDto(nodeCount);

        assertEquals(nodeCount.getUTCDate(), dto.getUTCDate());
        assertEquals(nodeCount.getTotalNodeCount(), dto.getTotalNodeCount());
    }

    @Test
    public void testToEntity() {
        NodeCountDto dto = new NodeCountDto();
        dto.setUTCDate("2023-01-01T00:00:00Z");
        dto.setTotalNodeCount("100");

        NodeCount nodeCount = NodeCountMapper.toEntity(dto);

        assertEquals(dto.getUTCDate(), nodeCount.getUTCDate());
        assertEquals(dto.getTotalNodeCount(), nodeCount.getTotalNodeCount());
    }
}