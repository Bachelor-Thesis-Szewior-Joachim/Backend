package org.example.backend.nft.service;

import org.example.backend.nft.entity.collection.Collection;
import org.example.backend.nft.entity.nft.NFT;
import org.example.backend.nft.entity.nft.NFTDto;
import org.example.backend.nft.entity.nft.owner.NFTOwner;
import org.example.backend.nft.entity.nft.trait.NFTTrait;
import org.example.backend.nft.mapper.nft.NFTMapper;
import org.example.backend.nft.repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NFTService {

    private final CollectionRepository collectionRepository;

    private final NFTRepository nftRepository;
    private final NFTOwnerRepository nftOwnerRepository;
    private final NFTTraitRepository nftTraitRepository;

    private final RestTemplate restTemplate;

    private static final String OPENSEA_API_URL = "https://api.opensea.io/api/v2/collection/";

    public NFTService(CollectionRepository collectionRepository, NFTRepository nftRepository, NFTOwnerRepository nftOwnerRepository, NFTTraitRepository nftTraitRepository, RestTemplate restTemplate) {
        this.collectionRepository = collectionRepository;
        this.nftRepository = nftRepository;
        this.nftOwnerRepository = nftOwnerRepository;
        this.nftTraitRepository = nftTraitRepository;
        this.restTemplate = restTemplate;
    }

    public List<NFTDto> findByContract(String contract) {
        List<NFT> nfts = nftRepository.findByContract(contract);
        return nfts.stream().map(NFTMapper::toDto).collect(Collectors.toList());
    }

    public Optional<NFTDto> findByIdentifier(String identifier) {
        NFT nft = nftRepository.findByIdentifier(identifier);
        return Optional.of(NFTMapper.toDto(nft));
    }

    public List<NFTDto> findByName(String name) {
        List<NFT> nfts = nftRepository.findByName(name);
        return nfts.stream().map(NFTMapper::toDto).collect(Collectors.toList());
    }

    public List<NFTDto> findByCollectionId(Long collectionId) {
        List<NFT> nfts = nftRepository.findByCollectionId(collectionId);
        return nfts.stream().map(NFTMapper::toDto).collect(Collectors.toList());
    }

    public void fetchAndSaveNFTs() {
        List<Collection> collections = (List<Collection>) collectionRepository.findAll();

        for (Collection collection : collections) {
            try {
                String url = UriComponentsBuilder.fromHttpUrl(OPENSEA_API_URL + collection.getSlug() + "/nfts")
                        .queryParam("limit", 5)
                        .build()
                        .toUriString();

                HttpHeaders headers = new HttpHeaders();
                headers.set("accept", "application/json");
                headers.set("x-api-key", "a48c02f835254acc9fdadfe443c16b26");

                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

                System.out.println(response.getBody());
                List<NFT> nfts = NFTMapper.mapJsonResponseToEntities(response.getBody(), collection);
                for (NFT nft : nfts) {
                    List<NFTOwner> nftOwners = nft.getOwners();
                    List<NFTTrait> nftTrait = nft.getTraits();
                    nftOwnerRepository.saveAll(nftOwners);
                    nftTraitRepository.saveAll(nftTrait);
                    nftRepository.save(nft);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error processing collection: " + collection.getSlug());
            }
        }
    }


}
