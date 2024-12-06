package org.example.backend.client.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.backend.blockchain.solana.accounts.service.SolanaAccountService;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.client.entity.ClientDto;
import org.example.backend.client.client.mapper.ClientMapper;
import org.example.backend.client.client.repository.ClientRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final SolanaAccountService solanaAccountService;
    private final JwtEncoder jwtEncoder;

    @Transactional
    public boolean registerUser(String username, String password) {
        if (clientRepository.findByUsername(username).isPresent()) {
            return false;
        }

        Map<String, String> solanaAccount = createSolanaAccount();
        String publicKey = solanaAccount.get("publicKey");
        String privateKey = solanaAccount.get("secretKey");

        Client client = new Client();
        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setEnabled(true);
        client.setPublicKey(publicKey);
        client.setPrivateKey(privateKey);

        clientRepository.save(client);
        return true;
    }

    public Client getClientByUsername(String username) {
        Optional<Client> clientOptional = clientRepository.findByUsername(username);
        return clientOptional.orElse(null);
    }

    public String createAccessToken(Client client) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("blockchain-backend")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(300))
                .subject(client.getUsername())
                .claim("publicKey", client.getPublicKey())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public Map<String, String> createSolanaAccount() {
        try {
            String url = "http://localhost:3001/createAccount";
             ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

             if (response.getBody() == null || response.getBody().isEmpty()) {
                 throw new RuntimeException("Empty response from Node.js service");
             }

             ObjectMapper objectMapper = new ObjectMapper();
             Map<String, String> result = objectMapper.readValue(response.getBody(), Map.class);

             if (!result.containsKey("publicKey") || !result.containsKey("secretKey")) {
                 throw new RuntimeException("Response from Node.js service does not contain required keys");
             }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    public Optional<String> tryTransaction(String publicKey) {
        try {
            String url = "http://localhost:3001/createTransaction";
            Map<String, String> requestBody = Map.of("publicKey", publicKey);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<String> requestAirdrop(String publicKey) {
        try {
            String url = "http://localhost:3001/requestAirdrop";

            Map<String, String> requestBody = Map.of("publicKey", publicKey);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public ClientDto getClientById(UUID id) {
        Optional<Client> clientOptional = clientRepository.findById(id);


        if (clientOptional.isPresent()) {
            String publicKey = clientOptional.get().getPublicKey();
            Optional<String> balance = solanaAccountService.getDevnetAccountBalance(publicKey);
            Client client = clientOptional.get();
            if (balance.isPresent()) {
                client.setBalance(Double.parseDouble(balance.get()));
            } else {
                client.setBalance((double) 0);
            }
            return ClientMapper.toDto(client);
        } else {
            return null;
        }
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
