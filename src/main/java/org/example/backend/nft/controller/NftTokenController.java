package org.example.backend.nft.controller;

import org.example.backend.nft.entity.nft.NFTDto;
import org.example.backend.nft.service.NFTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nft-statistics")
public class NftTokenController {

    private final NFTService nftService;

    public NftTokenController(NFTService nftService) {
        this.nftService = nftService;
    }

    @GetMapping("/contract/{contract}")
    public ResponseEntity<List<NFTDto>> getNFTsByContract(@PathVariable String contract) {
        List<NFTDto> nfts = nftService.findByContract(contract);
        return ResponseEntity.ok(nfts);
    }

    // Get NFT by identifier
    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<NFTDto> getNFTByIdentifier(@PathVariable String identifier) {
        Optional<NFTDto> nft = nftService.findByIdentifier(identifier);
        return nft.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get NFT by creator
    @GetMapping("/name/{name}")
    public ResponseEntity<List<NFTDto>> getNFTsByName(@PathVariable String name) {
        List<NFTDto> nfts = nftService.findByName(name);
        return ResponseEntity.ok(nfts);
    }

    @GetMapping("/collection/{collectionId}")
    public ResponseEntity<List<NFTDto>> getNFTsByName(@PathVariable Long collectionId) {
        List<NFTDto> nfts = nftService.findByCollectionId(collectionId);
        return ResponseEntity.ok(nfts);
    }

}
