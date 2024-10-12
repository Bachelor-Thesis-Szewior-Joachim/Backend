package org.example.backend.blockchain.ethereum.contract.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContractDto {
    String contractAddress;
    String contractCreator;
    String txHash;
}