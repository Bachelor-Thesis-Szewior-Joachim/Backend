package org.example.backend.blockchain.block.repository;

import org.example.backend.blockchain.block.entity.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
}
