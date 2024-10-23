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

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            // Path to the JavaScript script (adjust the path if needed)
            String scriptPath = "scripts/createSolanaAccount.js";

            // Run the script using Node.js
            ProcessBuilder processBuilder = new ProcessBuilder("node", scriptPath);
            Process process = processBuilder.start();

            // Read the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Convert the output (JSON) into a Map
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> result = objectMapper.readValue(output.toString(), Map.class);

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
            String scriptPath = "solanaScripts/createTransaction.js";

            ProcessBuilder processBuilder = new ProcessBuilder("node", scriptPath, information, publicKey);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            String base64EncodedTransaction = output.toString();
            System.out.println("Serialized Transaction: " + base64EncodedTransaction);

            String url = "https://solana-devnet.g.alchemy.com/v2/NHMqw3IwndcH6j0c4Y23KgZx50v59-ts";
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("jsonrpc", "2.0");
            requestBody.put("id", 1);
            requestBody.put("method", "simulateTransaction");
            requestBody.put("params", Arrays.asList(base64EncodedTransaction, Map.of("sigVerify", true)));

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            System.out.println("Simulate Transaction Response: " + response.getBody());
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    public Optional<String> requestAirdrop(String publicKey) {
        try {
            // Path to the JavaScript script (adjust the path if needed)
            String scriptPath = "scripts/requestAirdrop.js";

            // Run the script using Node.js, passing the public key as an argument
            ProcessBuilder processBuilder = new ProcessBuilder("node", scriptPath, publicKey);
            Process process = processBuilder.start();

            // Read the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Convert the output (JSON) into a String
            String jsonResult = output.toString();
            System.out.println("Airdrop Script Output: " + jsonResult);

            return Optional.ofNullable(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
