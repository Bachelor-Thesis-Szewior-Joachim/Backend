package org.example.backend.client.client.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ClientDto {

    private UUID id;
    private String username;
    private String password;
    private List<String> seedPhrase = new LinkedList<>();
}
