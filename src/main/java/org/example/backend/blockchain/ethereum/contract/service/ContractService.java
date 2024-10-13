package org.example.backend.blockchain.ethereum.contract.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.contract.entity.contract.Contract;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.example.backend.blockchain.ethereum.contract.entity.response.ContractResponse;
import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCode;
import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCodeDto;
import org.example.backend.blockchain.ethereum.contract.mapper.ContractMapper;
import org.example.backend.blockchain.ethereum.contract.mapper.SourceCodeMapper;
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

    public Optional<SourceCodeDto> getSourceCode(String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getsourcecode")
                .queryParam("address", contractAddress)
                .queryParam("apikey", apiKey);

        try {
            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            System.out.println(response);

            JsonNode resultNode = jsonNode.get("result").get(0);

            SourceCode sourceCode = new SourceCode();
            sourceCode.setSourceCode(resultNode.get("SourceCode").asText());
            sourceCode.setABI(resultNode.get("ABI").asText());

            return Optional.of(SourceCodeMapper.mapSourceCodeToSourceCodeDto(sourceCode));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
