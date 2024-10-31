package org.example.backend.client.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.client.client.entity.Client;
import org.example.backend.client.client.repository.ClientRepository;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
    }

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

    public Map<String, String> createSolanaAccount() {
        try {
            String url = "http://nodejs_service:3000/createAccount";
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> clientOptional = clientRepository.findByUsername(username);
        if (clientOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Client client = clientOptional.get();
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                client.isEnabled(),
                true,
                true,
                true,
                new ArrayList<>()
        );
    }

    public Optional<String> tryTransaction(String information, String publicKey) {
        try {
            String url = "http://nodejs_service:3000/createTransaction";
            Map<String, String> requestBody = Map.of("information", information, "publicKey", publicKey);

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
            String url = "http://nodejs_service:3000/requestAirdrop";
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
}
