package org.example.backend.blockchain.ethereum.contract.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String getContractSourceCode(String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getsourcecode")
                .queryParam("address", contractAddress)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    public String getContractCreationInfo(String contractAddresses) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "contract")
                .queryParam("action", "getcontractcreation")
                .queryParam("contractaddresses", contractAddresses)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }


}
