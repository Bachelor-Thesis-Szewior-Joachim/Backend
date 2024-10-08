package org.example.backend.blockchain.solana.accounts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SolanaAccountService {


    private final RestTemplate restTemplate;

    public SolanaAccountService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String SOLANA_RPC_URL = "https://api.mainnet-beta.solana.com";

    private HttpEntity<String> createRequestBody(String method, Object[] params) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "2.0");
        body.put("id", 1);
        body.put("method", method);
        body.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(new ObjectMapper().writeValueAsString(body), headers);
    }

    public String getAccountInfo(String accountAddress) throws Exception {
        String method = "getAccountInfo";
        // Add "encoding" parameter to specify how the account data should be returned (base64 or jsonParsed)
        Object[] params = new Object[]{accountAddress, Map.of("encoding", "base64")};
        HttpEntity<String> request = createRequestBody(method, params);
        return restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
    }

}
