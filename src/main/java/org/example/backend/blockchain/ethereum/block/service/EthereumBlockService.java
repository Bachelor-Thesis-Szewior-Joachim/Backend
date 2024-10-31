package org.example.backend.blockchain.ethereum.block.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlock;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;
import org.example.backend.blockchain.ethereum.block.mapper.EthereumBlockMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class EthereumBlockService {

    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumBlockService() {
        this.restTemplate = new RestTemplate();
    }

    public Optional<EthereumBlockDto> getBlockByNumber(long blockNumber) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "proxy")
                .queryParam("action", "eth_getBlockByNumber")
                .queryParam("tag", "0x" + Long.toHexString(blockNumber))
                .queryParam("boolean", "false")
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode resultNode = jsonNode.get("result");

            if (resultNode != null) {
                return Optional.ofNullable(EthereumBlockMapper.mapJsonToEthereumBlockDto(resultNode));
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<List<EthereumBlockDto>> getMinedBlocks(String address, String blockType) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "account")
                .queryParam("action", "getminedblocks")
                .queryParam("address", address)
                .queryParam("blocktype", blockType)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode resultNode = jsonNode.get("result");

            if (resultNode != null) {
                return Optional.ofNullable(EthereumBlockMapper.mapMinedBlocks(resultNode));
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<String> getEthSupply() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethsupply")
                .queryParam("apikey", apiKey);


        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            return Optional.ofNullable(jsonNode.get("result").asText());
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<String> getEthPrice() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethprice")
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            return Optional.ofNullable(jsonNode.path("result").path("ethusd").asText());
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns the estimated time remaining, in seconds, until a certain block is mined.
    public String getBlockCountdown(int blockNumber) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "block")
                .queryParam("action", "getblockcountdown")
                .queryParam("blockno", blockNumber)
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    // Returns the block number that was mined at a certain timestamp.
    public String getBlockByTimestamp(long timestamp, String closest) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "block")
                .queryParam("action", "getblocknobytime")
                .queryParam("timestamp", timestamp)
                .queryParam("closest", closest)  // Can be 'before' or 'after'
                .queryParam("apikey", apiKey);

        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }


}
