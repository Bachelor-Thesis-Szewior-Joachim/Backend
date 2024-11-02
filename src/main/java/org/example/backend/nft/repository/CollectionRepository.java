package org.example.backend.nft.repository;

import org.example.backend.nft.entity.collection.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
}
