package org.example.backend.blockchain.solana.block.repository;

import org.example.backend.blockchain.solana.block.entity.SolanaBlock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolanaBlockRepository extends CrudRepository<SolanaBlock, Long> {
}
