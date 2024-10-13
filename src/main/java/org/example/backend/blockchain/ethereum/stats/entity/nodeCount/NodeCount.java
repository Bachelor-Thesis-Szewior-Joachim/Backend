package org.example.backend.blockchain.ethereum.stats.entity.nodeCount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NodeCount {
    private String UTCDate;
    private String totalNodeCount;

    public NodeCount() {
    }
}
