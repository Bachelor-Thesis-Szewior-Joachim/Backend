package org.example.backend.blockchain.solana.transaction.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;
import org.example.backend.blockchain.solana.transaction.mapper.JsonSolanaSignatureMapper;
import org.example.backend.blockchain.solana.transaction.mapper.JsonSolanaTransactionMapper;
import org.example.backend.blockchain.solana.transaction.mapper.SolanaTransactionMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SolanaTransactionService {

    private final RestTemplate restTemplate;

    private static final String SOLANA_RPC_URL = "https://api.mainnet-beta.solana.com";

    public SolanaTransactionService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpEntity<String> createRequestBody(String method, Object[] params) {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "2.0");
        body.put("id", 1);
        body.put("method", method);
        body.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return new HttpEntity<>(new ObjectMapper().writeValueAsString(body), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SolanaTransactionDto> getTransaction(String transactionSignature) {
        String method = "getTransaction";
        Object[] params = new Object[]{transactionSignature};
        HttpEntity<String> request = createRequestBody(method, params);
        String response =  restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
        SolanaTransaction solanaTransaction= JsonSolanaTransactionMapper.mapJsonToSolanaTransaction(response);
        return Optional.ofNullable(SolanaTransactionMapper.toDto(solanaTransaction));
    }

    public Optional<Map<String, String>> getSignatureStatuses(List<String> signatures) {
        String method = "getSignatureStatuses";
        Map<String, Boolean> searchTransactionHistory = new HashMap<>();
        searchTransactionHistory.put("searchTransactionHistory", true);
        Object[] params = new Object[]{signatures, searchTransactionHistory};
        HttpEntity<String> request = createRequestBody(method, params);
        String response =  restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
        Map<String, String> mappedData = JsonSolanaSignatureMapper.mapJsonToSolanaSignature(response);
        return Optional.ofNullable(mappedData);
    }

    public String getSignaturesForAddress(String signature) {
        String method = "getSignaturesForAddress";
        Object[] params = new Object[]{signature};
        HttpEntity<String> request = createRequestBody(method, params);
        return restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
    }
}
