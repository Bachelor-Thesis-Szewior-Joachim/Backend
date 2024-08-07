package org.example.backend.blockchain.accounts.controller;

import org.example.backend.blockchain.accounts.entity.Account;
import org.example.backend.exception.error.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountInfo(@PathVariable Long id) {

        return new ResponseEntity<>(new Error("Couldn't get account"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {

        return new ResponseEntity<>(new Error("Couldn't create account"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {

        return new ResponseEntity<>(new Error("Couldn't login"), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {

        return new ResponseEntity<>(new Error("Couldn't create account"), HttpStatus.NOT_FOUND);
    }
}
