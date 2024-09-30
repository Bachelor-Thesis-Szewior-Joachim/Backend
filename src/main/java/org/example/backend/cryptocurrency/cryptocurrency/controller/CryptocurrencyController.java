package org.example.backend.cryptocurrency.cryptocurrency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/general-nft-statistics")
public class CryptocurrencyController {

    @GetMapping
    public ResponseEntity<?> getTokenInfo(@PathVariable String name) {

        return new ResponseEntity<>(new Error("Couldn't find token info"), HttpStatus.NOT_FOUND);
    }


}
