package org.example.backend.cryptocurrency.cryptocurrency.repository;

import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricalDataRepository extends CrudRepository<HistoricalData, Long> {
    List<HistoricalData> findByCmcId(Long cmcId);
    List<HistoricalData> findByDate(String date);
}
