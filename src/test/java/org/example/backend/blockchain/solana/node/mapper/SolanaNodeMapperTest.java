package org.example.backend.blockchain.solana.node.mapper;

import org.example.backend.blockchain.solana.node.entity.SolanaClusterNode;
import org.example.backend.blockchain.solana.node.entity.SolanaClusterNodeDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaNodeMapperTest {

    @Test
    public void testToDto() {
        SolanaClusterNode solanaClusterNode = SolanaClusterNode.builder()
                .featureSet(123L)
                .gossip("gossip")
                .pubkey("pubkey")
                .pubsub("pubsub")
                .rpc("rpc")
                .serveRepair("serveRepair")
                .shredVersion(1)
                .tpu("tpu")
                .tpuForwards("tpuForwards")
                .tpuForwardsQuic("tpuForwardsQuic")
                .tpuQuic("tpuQuic")
                .tpuVote("tpuVote")
                .tvu("tvu")
                .version("version")
                .build();

        SolanaClusterNodeDto solanaClusterNodeDto = SolanaNodeMapper.toDto(solanaClusterNode);

        assertEquals(solanaClusterNode.getFeatureSet(), solanaClusterNodeDto.getFeatureSet());
        assertEquals(solanaClusterNode.getGossip(), solanaClusterNodeDto.getGossip());
        assertEquals(solanaClusterNode.getPubkey(), solanaClusterNodeDto.getPubkey());
        assertEquals(solanaClusterNode.getPubsub(), solanaClusterNodeDto.getPubsub());
        assertEquals(solanaClusterNode.getRpc(), solanaClusterNodeDto.getRpc());
        assertEquals(solanaClusterNode.getServeRepair(), solanaClusterNodeDto.getServeRepair());
        assertEquals(solanaClusterNode.getShredVersion(), solanaClusterNodeDto.getShredVersion());
        assertEquals(solanaClusterNode.getTpu(), solanaClusterNodeDto.getTpu());
        assertEquals(solanaClusterNode.getTpuForwards(), solanaClusterNodeDto.getTpuForwards());
        assertEquals(solanaClusterNode.getTpuForwardsQuic(), solanaClusterNodeDto.getTpuForwardsQuic());
        assertEquals(solanaClusterNode.getTpuQuic(), solanaClusterNodeDto.getTpuQuic());
        assertEquals(solanaClusterNode.getTpuVote(), solanaClusterNodeDto.getTpuVote());
        assertEquals(solanaClusterNode.getTvu(), solanaClusterNodeDto.getTvu());
        assertEquals(solanaClusterNode.getVersion(), solanaClusterNodeDto.getVersion());
    }

    @Test
    public void testToEntity() {
        SolanaClusterNodeDto solanaClusterNodeDto = SolanaClusterNodeDto.builder()
                .featureSet(123L)
                .gossip("gossip")
                .pubkey("pubkey")
                .pubsub("pubsub")
                .rpc("rpc")
                .serveRepair("serveRepair")
                .shredVersion(1)
                .tpu("tpu")
                .tpuForwards("tpuForwards")
                .tpuForwardsQuic("tpuForwardsQuic")
                .tpuQuic("tpuQuic")
                .tpuVote("tpuVote")
                .tvu("tvu")
                .version("version")
                .build();

        SolanaClusterNode solanaClusterNode = SolanaNodeMapper.toEntity(solanaClusterNodeDto);

        assertEquals(solanaClusterNodeDto.getFeatureSet(), solanaClusterNode.getFeatureSet());
        assertEquals(solanaClusterNodeDto.getGossip(), solanaClusterNode.getGossip());
        assertEquals(solanaClusterNodeDto.getPubkey(), solanaClusterNode.getPubkey());
        assertEquals(solanaClusterNodeDto.getPubsub(), solanaClusterNode.getPubsub());
        assertEquals(solanaClusterNodeDto.getRpc(), solanaClusterNode.getRpc());
        assertEquals(solanaClusterNodeDto.getServeRepair(), solanaClusterNode.getServeRepair());
        assertEquals(solanaClusterNodeDto.getShredVersion(), solanaClusterNode.getShredVersion());
        assertEquals(solanaClusterNodeDto.getTpu(), solanaClusterNode.getTpu());
        assertEquals(solanaClusterNodeDto.getTpuForwards(), solanaClusterNode.getTpuForwards());
        assertEquals(solanaClusterNodeDto.getTpuForwardsQuic(), solanaClusterNode.getTpuForwardsQuic());
        assertEquals(solanaClusterNodeDto.getTpuQuic(), solanaClusterNode.getTpuQuic());
        assertEquals(solanaClusterNodeDto.getTpuVote(), solanaClusterNode.getTpuVote());
        assertEquals(solanaClusterNodeDto.getTvu(), solanaClusterNode.getTvu());
        assertEquals(solanaClusterNodeDto.getVersion(), solanaClusterNode.getVersion());
    }

    @Test
    public void testMapJsonResponseToSolanaClusterNodeDto() throws Exception {
        String jsonResponse = "{ \"result\": [ { \"featureSet\": 123, \"gossip\": \"gossip\", \"pubkey\": \"pubkey\", \"pubsub\": \"pubsub\", \"rpc\": \"rpc\", \"serveRepair\": \"serveRepair\", \"shredVersion\": 1, \"tpu\": \"tpu\", \"tpuForwards\": \"tpuForwards\", \"tpuForwardsQuic\": \"tpuForwardsQuic\", \"tpuQuic\": \"tpuQuic\", \"tpuVote\": \"tpuVote\", \"tvu\": \"tvu\", \"version\": \"version\" } ] }";

        List<SolanaClusterNodeDto> solanaClusterNodeDtos = SolanaNodeMapper.mapJsonResponseToSolanaClusterNodeDto(jsonResponse);

        assertEquals(1, solanaClusterNodeDtos.size());
        SolanaClusterNodeDto solanaClusterNodeDto = solanaClusterNodeDtos.get(0);

        assertEquals(123L, solanaClusterNodeDto.getFeatureSet());
        assertEquals("gossip", solanaClusterNodeDto.getGossip());
        assertEquals("pubkey", solanaClusterNodeDto.getPubkey());
        assertEquals("pubsub", solanaClusterNodeDto.getPubsub());
        assertEquals("rpc", solanaClusterNodeDto.getRpc());
        assertEquals("serveRepair", solanaClusterNodeDto.getServeRepair());
        assertEquals(1, solanaClusterNodeDto.getShredVersion());
        assertEquals("tpu", solanaClusterNodeDto.getTpu());
        assertEquals("tpuForwards", solanaClusterNodeDto.getTpuForwards());
        assertEquals("tpuForwardsQuic", solanaClusterNodeDto.getTpuForwardsQuic());
        assertEquals("tpuQuic", solanaClusterNodeDto.getTpuQuic());
        assertEquals("tpuVote", solanaClusterNodeDto.getTpuVote());
        assertEquals("tvu", solanaClusterNodeDto.getTvu());
        assertEquals("version", solanaClusterNodeDto.getVersion());
    }
}