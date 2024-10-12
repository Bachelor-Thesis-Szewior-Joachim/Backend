package org.example.backend.blockchain.ethereum.accounts.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    public String getEtherBalanceAndTransactionHistory(String address) {
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

        return "Balance: " + balanceResponse + "\nTransaction History: " + transactionHistoryResponse;
    }

    //Returns the current balance of an ERC-20 token of an address.
    public String getTokenBalance(String address, String contractAddress) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokenbalance")
                .queryParam("contractaddress", contractAddress)
                .queryParam("address", address)
                .queryParam("tag", "latest")
                .queryParam("apikey", apiKey);

        Optional<String> ethereumAccountOptional = Optional.ofNullable(restTemplate.getForObject(uriBuilder.toUriString(), String.class));

        System.out.println(ethereumAccountOptional.toString());

        if (ethereumAccountOptional.isPresent()) {
            return ethereumAccountOptional.get();
        } else {
            return null;
        }
    }

    //This call retrieves all ERC-20 token transfers involving the given Ethereum address (address), across any token
    // contract, within the specified block range (startblock to endblock).
    public String getERC20TokenTransfers(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokentx")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    //This call retrieves all ERC-721 token transfers involving the given Ethereum address (address), across any token
    // contract, within the specified block range (startblock to endblock).
    public String getERC721TokenTransfers(String address, int startBlock, int endBlock, String sort) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "tokennfttx")
                .queryParam("address", address)
                .queryParam("startblock", startBlock)
                .queryParam("endblock", endBlock)
                .queryParam("sort", sort)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
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
