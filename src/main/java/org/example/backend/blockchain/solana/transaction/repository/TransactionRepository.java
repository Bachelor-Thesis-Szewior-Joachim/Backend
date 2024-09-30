package org.example.backend.blockchain.solana.transaction.repository;

import org.example.backend.blockchain.solana.transaction.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
