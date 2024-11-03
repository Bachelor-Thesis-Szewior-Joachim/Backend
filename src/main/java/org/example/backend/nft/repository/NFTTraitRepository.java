package org.example.backend.nft.repository;

import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTTraitRepository extends CrudRepository<NFTTrait, Long> {
}
