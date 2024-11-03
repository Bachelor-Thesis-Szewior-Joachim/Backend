package org.example.backend.nft.repository;

import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTOwnerRepository extends CrudRepository<NFTOwner, Long> {
}
