package org.example.backend.cryptocurrency.globalMarket.repository;

import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FearAndGreedRepository extends CrudRepository<FearAndGreed, Long> {
}
