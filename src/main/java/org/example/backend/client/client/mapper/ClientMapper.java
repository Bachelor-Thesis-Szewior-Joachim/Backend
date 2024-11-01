package org.example.backend.client.client.mapper;

import org.example.backend.client.client.entity.Client;
import org.example.backend.client.client.entity.ClientDto;

public class ClientMapper {

    public static ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .username(client.getUsername())
                .password(client.getPassword())
                .enabled(client.isEnabled())
                .publicKey(client.getPublicKey())
                .privateKey(client.getPrivateKey())
                .balance(client.getBalance())
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return Client.builder()
                .id(clientDto.getId())
                .username(clientDto.getUsername())
                .password(clientDto.getPassword())
                .enabled(clientDto.isEnabled())
                .publicKey(clientDto.getPublicKey())
                .privateKey(clientDto.getPrivateKey())
                .balance(clientDto.getBalance())
                .build();
    }
}
