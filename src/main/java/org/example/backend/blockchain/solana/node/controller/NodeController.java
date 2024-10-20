package org.example.backend.blockchain.solana.node.controller;

import org.example.backend.blockchain.solana.node.entity.SolanaClusterNodeDto;
import org.example.backend.blockchain.solana.node.service.NodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/solana/node")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    //Returns information about all the nodes participating in the cluster
    @GetMapping("/getClusterNodes")
    public ResponseEntity<List<SolanaClusterNodeDto>> getClusterNodes() {
        Optional<List<SolanaClusterNodeDto>> clusterNodesOptional = nodeService.getClusterNodes();
        return clusterNodesOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
}
