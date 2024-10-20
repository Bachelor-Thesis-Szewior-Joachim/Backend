package org.example.backend.blockchain.solana.block.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.block.mapper.SolanaSimpleJsonMapper;
import org.example.backend.blockchain.solana.block.mapper.SolanaCommitmentMapper;
import org.example.backend.blockchain.solana.block.mapper.SolanaValueMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
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

    public Optional<String> getBlock(long blockNumber) {

        try{
            String method = "getBlock";

            Object[] params = new Object[]{
                    blockNumber,
                    Map.of(
                            "encoding", "json",
                            "transactionDetails", "full",
                            "maxSupportedTransactionVersion", 0
                    )
            };

            HttpEntity<String> request = createRequestBody(method, params);
            return Optional.ofNullable(restTemplate.postForObject(SOLANA_RPC_URL, request, String.class));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    public Optional<Map<String, Object>> getBlockProduction() {
        try {
            String method = "getBlockProduction";

            Object[] params = new Object[]{

            };
            HttpEntity<String> request = createRequestBody(method, params);
            Optional<String> optionalString =  Optional.ofNullable(restTemplate.postForObject(SOLANA_RPC_URL, request, String.class));
            if (optionalString.isPresent()) {
                Map<String, Object> blockProductionResponse = SolanaValueMapper.mapJsonToValue(optionalString.get());
                return Optional.ofNullable(blockProductionResponse);
            }
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    public Optional<String> getBlockTime(long blockNumber) {

        try {
            String method = "getBlockTime";

            Object[] params = new Object[]{
                blockNumber
            };
            HttpEntity<String> request = createRequestBody(method, params);
            String jsonResponse = restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
            String blockTime = SolanaSimpleJsonMapper.mapJsonToValue(jsonResponse);
            return Optional.ofNullable(blockTime);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    public Optional<Map<String, String>> getBlockCommitment(long blockNumber) {

        try {
            String method = "getBlockCommitment";

            Object[] params = new Object[]{
                    blockNumber
            };
            HttpEntity<String> request = createRequestBody(method, params);
            String jsonResponse = restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
            Map<String, String> commitmentMap = SolanaCommitmentMapper.mapJsonToValue(jsonResponse);
            return Optional.ofNullable(commitmentMap);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    public Optional<String> getBlocksWithLimit(Long startingBlock, Long amount) {

        try {
            String method = "getBlocksWithLimit";
            Object[] params = new Object[]{
                    startingBlock,amount
            };
            HttpEntity<String> request = createRequestBody(method, params);
            String jsonResponse = restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
            String blocksWithLimit = SolanaSimpleJsonMapper.mapJsonToValue(jsonResponse);
            return Optional.ofNullable(blocksWithLimit);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    public Optional<String> getBlockHeight() {

        try {
            String method = "getBlockHeight";
            Object[] params = new Object[]{
            };
            HttpEntity<String> request = createRequestBody(method, params);
            String jsonResponse = restTemplate.postForObject(SOLANA_RPC_URL, request, String.class);
            String blockHeight = SolanaSimpleJsonMapper.mapJsonToValue(jsonResponse);
            return Optional.ofNullable(blockHeight);        }
        catch (Exception e) {
        }
        return Optional.empty();
    }

}
