package org.example.backend.client.client.repository;

import org.example.backend.client.client.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
