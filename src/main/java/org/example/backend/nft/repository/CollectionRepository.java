package org.example.backend.nft.repository;

import org.example.backend.nft.entity.collection.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    Collection findBySlug(String slug);

    List<Collection> findByBlockchain(String blockchain);
}
