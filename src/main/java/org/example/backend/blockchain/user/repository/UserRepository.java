package org.example.backend.blockchain.user.repository;

import org.example.backend.blockchain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
