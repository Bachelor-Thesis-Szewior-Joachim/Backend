package org.example.backend.nft.repository;

import org.example.backend.nft.entity.nft.NFT;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NFTRepository extends CrudRepository<NFT, Long> {

    List<NFT> findByContract(String contract);

    NFT findByIdentifier(String identifier);

    List<NFT> findByName(String creator);

    List<NFT> findByCollectionId(Long collection_id);
}
