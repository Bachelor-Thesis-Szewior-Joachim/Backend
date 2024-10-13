package org.example.backend.blockchain.ethereum.stats.mapper;

import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCount;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCountDto;

public class NodeCountMapper {

    public static NodeCountDto toDto(NodeCount nodeCount) {
        NodeCountDto dto = new NodeCountDto();
        dto.setUTCDate(nodeCount.getUTCDate());
        dto.setTotalNodeCount(nodeCount.getTotalNodeCount());
        return dto;
    }

    public static NodeCount toEntity(NodeCountDto dto) {
        NodeCount nodeCount = new NodeCount();
        nodeCount.setUTCDate(dto.getUTCDate());
        nodeCount.setTotalNodeCount(dto.getTotalNodeCount());
        return nodeCount;
    }
}

