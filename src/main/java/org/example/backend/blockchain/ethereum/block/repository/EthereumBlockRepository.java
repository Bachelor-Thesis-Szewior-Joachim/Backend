package org.example.backend.blockchain.ethereum.block.repository;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthereumBlockRepository extends CrudRepository<EthereumBlock, Long> {
}
