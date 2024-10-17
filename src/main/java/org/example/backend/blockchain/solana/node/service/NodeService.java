package org.example.backend.blockchain.solana.node.service;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class NodeService {
    public Optional<Map<String, String>> getClusterNodes() {
        return Optional.empty();
    }
}
