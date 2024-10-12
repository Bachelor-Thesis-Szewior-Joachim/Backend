package org.example.backend.blockchain.ethereum.contract.service;

import org.example.backend.blockchain.ethereum.contract.entity.contract.Contract;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponse;
import org.example.backend.blockchain.ethereum.contract.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public ContractService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ContractDto> getContractsCreationInfo(List<String> contractAddresses) {
        String joinedAddresses = String.join(",", contractAddresses);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getcontractcreation")
                .queryParam("contractaddresses", joinedAddresses)  // Use joined addresses here
                .queryParam("apikey", apiKey);

        ContractResponse response = restTemplate.exchange(
                uriBuilder.build().toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ContractResponse>() {}
        ).getBody();

        if (response != null && response.getResult() != null) {
            return response.getResult().stream()
                    .map(contract -> ContractMapper.mapContractToContractDto(contract))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public Optional<String> getSourceCode(String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getsourcecode")
                .queryParam("address", contractAddress)
                .queryParam("apikey", apiKey);

        try {
            // Attempt to get the source code as a String
            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

            // Return the result wrapped in an Optional
            return Optional.ofNullable(response);
        } catch (Exception e) {
            // If there's an exception (e.g., contract not found), return an empty Optional
            return Optional.empty();
        }
    }


}
