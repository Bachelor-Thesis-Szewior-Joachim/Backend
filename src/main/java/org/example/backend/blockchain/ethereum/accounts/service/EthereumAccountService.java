package org.example.backend.blockchain.ethereum.accounts.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.accounts.mapper.EthereumAccountMapper;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EthereumAccountService {

    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumAccountService(@Qualifier("restTemplate")RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<EthereumAccountDto> getEtherBalanceAndTransactionHistory(String address) {
        UriComponentsBuilder balanceUriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "balance")
                .queryParam("address", address)
                .queryParam("tag", "latest")
                .queryParam("apikey", apiKey);

        String balanceResponse = restTemplate.getForObject(balanceUriBuilder.toUriString(), String.class);

        UriComponentsBuilder txUriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "txlist")
                .queryParam("address", address)
                .queryParam("startblock", "0")  // Fetch from block 0
                .queryParam("endblock", "99999999")  // Fetch until latest block
                .queryParam("sort", "asc")  // Sort by ascending order of block number
                .queryParam("apikey", apiKey);

        String transactionHistoryResponse = restTemplate.getForObject(txUriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode balanceNode = objectMapper.readTree(balanceResponse);
            JsonNode transactionHistoryNode = objectMapper.readTree(transactionHistoryResponse);

            return Optional.ofNullable(EthereumAccountMapper
                    .mapAccountToAccountDto(EthereumAccountMapper
                            .mapToEthereumAccount(balanceNode, transactionHistoryNode)));
        } catch (Exception e) {
            System.out.println("Error parsing Ethereum account data" + e);
            return Optional.empty();
        }
    }

    public Double getTokenBalance(String address, String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokenbalance")
                .queryParam("contractaddress", contractAddress)
                .queryParam("address", address)
                .queryParam("tag", "latest")
                .queryParam("apikey", apiKey);

        Optional<String> ethereumAccountOptional = Optional.ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), String.class));


        if (ethereumAccountOptional.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(ethereumAccountOptional.get());

                String resultString = rootNode.get("result").asText();

                return Double.valueOf(resultString);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing token balance response", e);
            }
        } else {
            return null;
        }
    }

    //This call retrieves all ERC-20 token transfers involving the given Ethereum address (address), across any token
    // contract, within the specified block range (startblock to endblock).
    public Optional<List<EthereumTransactionDto>> getERC20TokenTransfers(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokentx")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);




        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            // Parse the response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            if (resultNode != null && resultNode.isArray()) {
                List<EthereumTransactionDto> transactions = new ArrayList<>();

                for (JsonNode transactionNode : resultNode) {
                    transactions.add(EthereumAccountMapper.mapToEthereumTransactionDto(transactionNode));
                }

                return Optional.of(transactions);
            }
        } catch (Exception e) {
            // Handle parsing error
            throw new RuntimeException("Error parsing token transfer data", e);
        }

        return Optional.empty();    }

    //This call retrieves all ERC-721 token transfers involving the given Ethereum address (address), across any token
    // contract, within the specified block range (startblock to endblock).
    public Optional<List<EthereumTransactionDto>> getERC721TokenTransfers(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokennfttx")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            // Parse the response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            if (resultNode != null && resultNode.isArray()) {
                List<EthereumTransactionDto> transactions = new ArrayList<>();

                for (JsonNode transactionNode : resultNode) {
                    transactions.add(EthereumAccountMapper
                            .mapToEthereumTransactionDtoWithTokenIdAndNoValue(transactionNode));
                }

                return Optional.of(transactions);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error parsing token transfer data", e);
        }

        return Optional.empty();
    }


    //Get Blocks mined by address
    public String getBlocksMinedByAddress(String address) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "getminedblocks")
                .queryParam("address", address)
                .queryParam("blocktype", "blocks")
                .queryParam("page", 1)
                .queryParam("offset", 10)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }
}
