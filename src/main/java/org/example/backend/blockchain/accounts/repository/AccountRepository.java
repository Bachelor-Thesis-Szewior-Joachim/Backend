package org.example.backend.blockchain.accounts.repository;

import org.example.backend.blockchain.accounts.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}
