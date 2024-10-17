package org.example.backend.blockchain.solana.block.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.block.entity.SolanaBlockDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SolanaBlockService {
    private final RestTemplate restTemplate;

    private static final String SOLANA_RPC_URL = "https://api.mainnet-beta.solana.com";

    public SolanaBlockService(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

    public Optional<SolanaBlockDto> getBlock(long blockNumber) {

//        try{
//            String method = "getBlock";
//
//            Object[] params = new Object[]{
//                    blockNumber,
//                    Map.of(
//                            "encoding", "json",
//                            "transactionDetails", "full",
//                            "maxSupportedTransactionVersion", 0
//                    )
//            };
//
//            HttpEntity<String> request = createRequestBody(method, params);
//            return restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        return Optional.empty();
    }

    public Optional<SolanaBlockDto> getBlockProduction() {
        return Optional.empty();
    }

    public Optional<Long> getBlockTime(long blockNumber) {

        return Optional.empty();
    }

    public Optional<Map<String, Long>> getBlockCommitment(long blockNumber) {

        return Optional.empty();
    }

    public Optional<List<SolanaBlockDto>> getBlocksWithLimit(Long startingBlock, Long amount) {

        return Optional.empty();
    }

    public Optional<Long> getBlockHeight() {
        return Optional.empty();
    }

}
