package org.example.backend.blockchain.solana.node.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.node.entity.SolanaClusterNode;
import org.example.backend.blockchain.solana.node.entity.SolanaClusterNodeDto;


import java.util.ArrayList;
import java.util.List;

public class SolanaNodeMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SolanaClusterNodeDto toDto(SolanaClusterNode solanaClusterNode) {
        return SolanaClusterNodeDto.builder()
                .featureSet(solanaClusterNode.getFeatureSet())
                .gossip(solanaClusterNode.getGossip())
                .pubkey(solanaClusterNode.getPubkey())
                .pubsub(solanaClusterNode.getPubsub())
                .rpc(solanaClusterNode.getRpc())
                .serveRepair(solanaClusterNode.getServeRepair())
                .shredVersion(solanaClusterNode.getShredVersion())
                .tpu(solanaClusterNode.getTpu())
                .tpuForwards(solanaClusterNode.getTpuForwards())
                .tpuForwardsQuic(solanaClusterNode.getTpuForwardsQuic())
                .tpuQuic(solanaClusterNode.getTpuQuic())
                .tpuVote(solanaClusterNode.getTpuVote())
                .tvu(solanaClusterNode.getTvu())
                .version(solanaClusterNode.getVersion())
                .build();
    }

    public static SolanaClusterNode toEntity(SolanaClusterNodeDto solanaClusterNodeDto) {
        return SolanaClusterNode.builder().featureSet(solanaClusterNodeDto.getFeatureSet())
                .gossip(solanaClusterNodeDto.getGossip())
                .pubkey(solanaClusterNodeDto.getPubkey())
                .pubsub(solanaClusterNodeDto.getPubsub())
                .rpc(solanaClusterNodeDto.getRpc())
                .serveRepair(solanaClusterNodeDto.getServeRepair())
                .shredVersion(solanaClusterNodeDto.getShredVersion())
                .tpu(solanaClusterNodeDto.getTpu())
                .tpuForwards(solanaClusterNodeDto.getTpuForwards())
                .tpuForwardsQuic(solanaClusterNodeDto.getTpuForwardsQuic())
                .tpuQuic(solanaClusterNodeDto.getTpuQuic())
                .tpuVote(solanaClusterNodeDto.getTpuVote())
                .tvu(solanaClusterNodeDto.getTvu())
                .version(solanaClusterNodeDto.getVersion())
                .build();
    }

    public static List<SolanaClusterNodeDto> mapJsonResponseToSolanaClusterNodeDto(String jsonResponse) {

        List<SolanaClusterNodeDto> solanaClusterNodesDto = new ArrayList<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode resultNode = rootNode.path("result");

            if (resultNode.isArray()) {
                for (JsonNode node : resultNode) {
                    SolanaClusterNode solanaClusterNode = SolanaClusterNode.builder()
                            .featureSet(node.path("featureSet").asLong())
                            .gossip(node.path("gossip").asText())
                            .pubkey(node.path("pubkey").asText())
                            .pubsub(node.path("pubsub").isNull() ? null : node.path("pubsub").asText())
                            .rpc(node.path("rpc").isNull() ? null : node.path("rpc").asText())
                            .serveRepair(node.path("serveRepair").asText())
                            .shredVersion(node.path("shredVersion").asInt())
                            .tpu(node.path("tpu").asText())
                            .tpuForwards(node.path("tpuForwards").asText())
                            .tpuForwardsQuic(node.path("tpuForwardsQuic").asText())
                            .tpuQuic(node.path("tpuQuic").asText())
                            .tpuVote(node.path("tpuVote").asText())
                            .tvu(node.path("tvu").asText())
                            .version(node.path("version").asText())
                            .build();

                    solanaClusterNodesDto.add(toDto(solanaClusterNode));
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return solanaClusterNodesDto;
    }
}
