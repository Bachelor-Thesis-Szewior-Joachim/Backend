package org.example.backend.blockchain.token.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/{name}")
    public ResponseEntity<?> getTokenInfo(@PathVariable String name) {

        return new ResponseEntity<>(new Error("Couldn't find token info"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/supportedCoins")
    public ResponseEntity<?> listSupportedCoins() {

        return new ResponseEntity<>(new Error("Couldn't find supported coins"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String hash, @RequestParam String name) {

        return new ResponseEntity<>(new Error("Couldn't find coin"), HttpStatus.NOT_FOUND);
    }
}
