package org.example.backend.client.client.controller;

import org.example.backend.client.client.entity.Client;
import org.example.backend.client.client.entity.ClientDto;
import org.example.backend.client.client.entity.LoginResponse;
import org.example.backend.client.client.service.ClientService;
import org.example.backend.config.TokenType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;


    public static class RegisterRequest {
        public String username;
        public String password;
    }

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        String username = request.username;
        String password = request.password;

        System.out.println("Registering user: " + username);

        boolean success = clientService.registerUser(username, password);
        System.out.println("User registered: " + success);
        if (success) {
            return ResponseEntity.ok("User registered successfully. Redirecting to home...");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(Authentication authentication) throws Exception {

        Client client = clientService.getClientByUsername(authentication.getName());
        if (client == null) {
            throw new Exception("User not found");
        }
        System.out.println("Login user: ");
        return ResponseEntity.ok(LoginResponse.builder()
                .accessToken(clientService.createAccessToken(client))
                .tokenType(TokenType.Bearer)
                .build());
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
