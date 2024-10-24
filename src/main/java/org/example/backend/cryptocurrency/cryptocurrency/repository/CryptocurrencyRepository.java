package org.example.backend.cryptocurrency.cryptocurrency.repository;

import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptocurrencyRepository extends CrudRepository<Cryptocurrency, Long> {
    Cryptocurrency findByCmcId(Long cmcId);
}
