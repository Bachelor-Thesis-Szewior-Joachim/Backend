package org.example.backend.client.client.controller;

import org.example.backend.client.client.entity.ClientDto;
import org.example.backend.client.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;


    public ClientController(ClientService clientService, AuthenticationManager authenticationManager) {
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        boolean success = clientService.registerUser(username, password);
        if (success) {
            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(authReq);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return ResponseEntity.ok("User registered successfully. Redirecting to home...");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return ResponseEntity.ok("Login successful.");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    @GetMapping("/simulateTransaction")
    public ResponseEntity<String> simulateTransaction(@RequestParam String publicKey) {
        Optional<String> optionalString = clientService.tryTransaction(publicKey);
        if (optionalString.isPresent()) {
            return ResponseEntity.ok(optionalString.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/requestAirdrop")
    public ResponseEntity<String> requestAirdrop(@RequestParam String publicKey) {
        Optional<String> optionalString = clientService.requestAirdrop(publicKey);
        if (optionalString.isPresent()) {
            return ResponseEntity.ok(optionalString.get());
        } else {
            return ResponseEntity.status(404).body("Airdrop failed or account not found.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable UUID id) {
        ClientDto clientDto = clientService.getClientById(id);
        if (clientDto != null) {
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
