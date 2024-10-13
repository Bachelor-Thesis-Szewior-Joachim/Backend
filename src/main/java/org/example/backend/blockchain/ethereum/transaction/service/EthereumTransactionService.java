package org.example.backend.blockchain.ethereum.transaction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.example.backend.blockchain.ethereum.transaction.mapper.EthereumTransactionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EthereumTransactionService {
    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumTransactionService() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<List<EthereumTransactionDto>> getNormalTransactions(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "txlist")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            if (resultNode != null && resultNode.isArray()) {
                List<EthereumTransaction> transactions = new ArrayList<>();

                // Iterate over each transaction in the result array
                for (JsonNode transactionNode : resultNode) {
                    EthereumTransaction transaction = EthereumTransactionMapper.mapToEthereumTransaction(transactionNode);
                    transactions.add(transaction);
                }

                return Optional.of(transactions.stream()
                        .map(transaction -> EthereumTransactionMapper
                                .mapTransactionToTransactionDto(transaction))
                        .collect(Collectors.toList()));
            }
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error parsing Ethereum transactions", e);
        }

        return Optional.empty();
    }

    public Optional<List<EthereumTransactionDto>> getInternalTransactions(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "txlistinternal")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            if (resultNode != null && resultNode.isArray()) {
                List<EthereumTransaction> transactions = new ArrayList<>();

                for (JsonNode transactionNode : resultNode) {
                    EthereumTransaction transaction = EthereumTransactionMapper.mapToEthereumTraceTransaction(transactionNode);
                    transactions.add(transaction);
                }

                return Optional.of(transactions.stream()
                        .map(transaction -> EthereumTransactionMapper
                                .mapTransactionToTransactionDto(transaction))
                        .collect(Collectors.toList()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing Ethereum transaction traces", e);
        }

        return Optional.empty();
    }

    public Optional<String> estimateGas(String to, String value, String data) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "proxy")
                .queryParam("action", "eth_estimategas")
                .queryParam("to", to)
                .queryParam("value", value)
                .queryParam("data", data)
                .queryParam("apikey", apiKey);

        String response =  restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String estimateGas = jsonNode.path("result").asText();
            return Optional.ofNullable(estimateGas);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Map<String, String>> getContractExecutionStatus(String txHash) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "transaction")
                .queryParam("action", "getstatus")
                .queryParam("txhash", txHash)
                .queryParam("apikey", apiKey);

        // Fetch response from API
        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            // Create a map to store the status
            Map<String, String> statusMap = new HashMap<>();
            if (resultNode != null) {
                // Add "isError" and "errDescription" to the map
                statusMap.put("isError", resultNode.get("isError").asText());
                statusMap.put("errDescription", resultNode.get("errDescription").asText());
            }

            return Optional.ofNullable(statusMap);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error parsing contract execution status", e);
        }
    }

    public Optional<Map<String, String>> getTransactionReceiptStatus(String txHash) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "transaction")
                .queryParam("action", "gettxreceiptstatus")
                .queryParam("txhash", txHash)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            Map<String, String> statusMap = new HashMap<>();
            if (resultNode != null) {
                statusMap.put("status", resultNode.get("status").asText());
            }

            return Optional.ofNullable(statusMap);
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error parsing contract execution status", e);
        }
    }
}
