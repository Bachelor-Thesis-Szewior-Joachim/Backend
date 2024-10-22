package org.example.backend.client.client.repository;

import org.example.backend.client.client.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {

    Optional<Client> findByUsername(String username);
}
