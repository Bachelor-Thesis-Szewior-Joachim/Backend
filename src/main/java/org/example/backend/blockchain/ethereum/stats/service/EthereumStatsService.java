package org.example.backend.blockchain.ethereum.stats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSize;
import org.example.backend.blockchain.ethereum.stats.entity.chainSize.ChainSizeDto;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracle;
import org.example.backend.blockchain.ethereum.stats.entity.gasOracle.GasOracleDto;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCount;
import org.example.backend.blockchain.ethereum.stats.entity.nodeCount.NodeCountDto;
import org.example.backend.blockchain.ethereum.stats.mapper.ChainSizeMapper;
import org.example.backend.blockchain.ethereum.stats.mapper.GasOracleMapper;
import org.example.backend.blockchain.ethereum.stats.mapper.NodeCountMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EthereumStatsService {


    private final RestTemplate restTemplate;

    @Value("${etherscan.api.key}")
    private String apiKey;

    @Value("${etherscan.api.url}")
    private String apiUrl;

    public EthereumStatsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Returns the estimated gas needed for a transaction based on the specified gas price.
    public Optional<String> getGasEstimate(long gasPrice) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "gastracker")
                .queryParam("action", "gasestimate")
                .queryParam("gasprice", gasPrice)
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String gasEstimate = jsonNode.get("result").asText();
            return Optional.ofNullable(gasEstimate);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns the current gas price information, including safe, average, and fast gas prices in Gwei.
    public Optional<GasOracleDto> getGasOracle() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "gastracker")
                .queryParam("action", "gasoracle")
                .queryParam("apikey", apiKey);

        try {
            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode resultNode = jsonNode.get("result");

            GasOracle gasOracle = new GasOracle();
            gasOracle.setLastBlock(resultNode.get("LastBlock").asText());
            gasOracle.setSafeGasPrice(resultNode.get("SafeGasPrice").asText());
            gasOracle.setProposeGasPrice(resultNode.get("ProposeGasPrice").asText());
            gasOracle.setFastGasPrice(resultNode.get("FastGasPrice").asText());
            gasOracle.setSuggestBaseFee(resultNode.get("suggestBaseFee").asText());

            // Split gasUsedRatio string and convert it to a List
            String gasUsedRatio = resultNode.get("gasUsedRatio").asText();
            List<String> gasUsedRatioList = Arrays.asList(gasUsedRatio.split(","));
            gasOracle.setGasUsedRatio(gasUsedRatioList);

            return Optional.ofNullable(GasOracleMapper.mapGasOracleToGasOracleDto(gasOracle));

        } catch (Exception e) {
            e.printStackTrace(); // Optionally log the error
            return null;
        }
    }

    // Returns the total circulating supply of Ether (ETH).
    public Optional<String> getEthSupply() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "ethsupply")
                .queryParam("apikey", apiKey);

        String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String ethSupply = jsonNode.get("result").asText();
            return Optional.ofNullable(ethSupply);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Returns the Ethereum blockchain size between the specified start and end dates, sorted by ascending order.
    public List<ChainSizeDto> getChainSize(String startDate, String endDate, String clientType, String syncMode) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "chainsize")
                .queryParam("startdate", startDate)
                .queryParam("enddate", endDate)
                .queryParam("clienttype", clientType)
                .queryParam("syncmode", syncMode)
                .queryParam("sort", "asc")
                .queryParam("apikey", apiKey);

        try {
            // Call the API and get the response as a String
            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            // Map the JSON data to ChainSize objects
            List<ChainSize> chainSizes = new ArrayList<>();
            for (JsonNode node : resultNode) {
                ChainSize chainSize = new ChainSize();
                chainSize.setBlockNumber(node.get("blockNumber").asText());
                chainSize.setChainTimeStamp(node.get("chainTimeStamp").asText());
                chainSize.setChainSize(node.get("chainSize").asText());
                chainSize.setClientType(node.get("clientType").asText());
                chainSize.setSyncMode(node.get("syncMode").asText());
                chainSizes.add(chainSize);
            }

            // Convert the list of ChainSize entities to ChainSizeDto and return
            return ChainSizeMapper.toDtoList(chainSizes);

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    // Returns the total number of active nodes on the Ethereum network.
    public NodeCountDto getNodeCount() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("module", "stats")
                .queryParam("action", "nodecount")
                .queryParam("apikey", apiKey);

        try {
            // Call the API and get the response as a String
            String response = restTemplate.getForObject(uriBuilder.toUriString(), String.class);

            // Parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode resultNode = rootNode.get("result");

            // Map the JSON data to NodeCount object
            NodeCount nodeCount = new NodeCount();
            nodeCount.setUTCDate(resultNode.get("UTCDate").asText());
            nodeCount.setTotalNodeCount(resultNode.get("TotalNodeCount").asText());

            // Convert to NodeCountDto and return
            return NodeCountMapper.toDto(nodeCount);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null in case of error
        }
    }
}
