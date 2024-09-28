package org.example.backend.blockchain.client.repository;

import org.example.backend.blockchain.client.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
