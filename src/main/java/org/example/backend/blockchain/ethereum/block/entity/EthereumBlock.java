package org.example.backend.blockchain.ethereum.block.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumBlock {

    private String baseFeePerGas;
    private String blobGasUsed;
    private String difficulty;
    private String excessBlobGas;
    private String extraData;
    private String gasLimit;
    private String gasUsed;
    private String hash;
    private String logsBloom;
    private String miner;
    private String blockReward;
    private String mixHash;
    private String nonce;
    private String number;
    private String parentBeaconBlockRoot;
    private String parentHash;
    private String receiptsRoot;
    private String sha3Uncles;
    private String size;
    private String stateRoot;
    private String timestamp;
    private String totalDifficulty;
    private List<String> transactions;  // List of transaction hashes
    private String transactionsRoot;
    private List<Withdrawal> withdrawals;  // List of withdrawal objects
    private String withdrawalsRoot;

    public EthereumBlock() {

    }
}
