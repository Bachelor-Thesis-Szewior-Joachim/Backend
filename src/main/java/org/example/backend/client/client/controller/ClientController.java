package org.example.backend.client.client.controller;

import lombok.Getter;
import lombok.Setter;
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

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;


    @Getter
    @Setter
    public static class RegisterRequest {
        private String username;
        private String password;

        @Override
        public String toString() {
            return "RegisterRequest{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
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
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> payload) throws Exception {
        String username = payload.get("username");
        String password = payload.get("password");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (username == null || password == null) {
            throw new Exception("Username or password is missing");
        }

        Client client = clientService.getClientByUsername(username);
        if (client == null || !clientService.validatePassword(password, client.getPassword())) {
            throw new Exception("Invalid username or password");
        }

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
