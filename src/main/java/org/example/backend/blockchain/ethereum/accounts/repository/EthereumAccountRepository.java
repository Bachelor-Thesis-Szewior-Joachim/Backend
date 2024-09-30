package org.example.backend.blockchain.ethereum.accounts.repository;

import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthereumAccountRepository extends CrudRepository<EthereumAccount, Long> {

}
