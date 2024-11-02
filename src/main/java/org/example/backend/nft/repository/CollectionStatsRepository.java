package org.example.backend.nft.repository;

import org.example.backend.nft.entity.collection.stats.CollectionStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionStatsRepository extends CrudRepository<CollectionStats, Long> {
}
