package org.example.backend.blockchain.ethereum.transaction.repository;

import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthereumTransactionRepository extends CrudRepository<EthereumTransaction, Long> {
}
