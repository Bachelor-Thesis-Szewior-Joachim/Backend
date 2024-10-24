package org.example.backend.cryptocurrency.cryptocurrency.repository;

import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {
}
