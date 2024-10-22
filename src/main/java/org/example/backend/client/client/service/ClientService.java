package org.example.backend.client.client.service;

import org.example.backend.client.client.entity.Client;
import org.example.backend.client.client.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public boolean registerUser(String username, String password) {
        if (clientRepository.findByUsername(username).isPresent()) {
            // Username already exists
            return false;
        }

        Client client = new Client();
        client.setUsername(username);
        client.setPassword(passwordEncoder.encode(password));
        client.setEnabled(true);

        clientRepository.save(client);
        return true;
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
}
