package org.example.backend.tokens.nft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nft-statistics")
public class NftTokenController {

    @GetMapping
    public ResponseEntity<?> getNftInfo() {

        return new ResponseEntity<>(new Error("Couldn't find token info"), HttpStatus.NOT_FOUND);
    }


}
