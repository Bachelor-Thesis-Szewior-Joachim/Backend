package org.example.backend.cryptocurrency.cryptocurrency.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.Platform;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.CryptocurrencyMapper;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.HistoricalDataMapper;
import org.example.backend.cryptocurrency.cryptocurrency.repository.CryptocurrencyRepository;
import org.example.backend.cryptocurrency.cryptocurrency.repository.HistoricalDataRepository;
import org.example.backend.cryptocurrency.cryptocurrency.repository.PlatformRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyService {

    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private final RestTemplate restTemplate;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final HistoricalDataRepository historicalDataRepository;
    private final PlatformRepository platformRepository;


    public CryptocurrencyService(RestTemplate restTemplate, CryptocurrencyRepository cryptocurrencyRepository, HistoricalDataRepository historicalDataRepository, PlatformRepository platformRepository) {
        this.restTemplate = restTemplate;
        this.cryptocurrencyRepository = cryptocurrencyRepository;
        this.historicalDataRepository = historicalDataRepository;
        this.platformRepository = platformRepository;
    }

    public Optional<List<CryptocurrencyDto>> getCryptocurrencyRanking(Long startIndex, Long lastIndex) {

        List<Cryptocurrency> cryptocurrencyList = new ArrayList<>();
        for (Long currentIndex=startIndex;currentIndex<=lastIndex;currentIndex++) {
            Optional<Cryptocurrency> cryptocurrencyOptional = cryptocurrencyRepository.findById(currentIndex);
            if (cryptocurrencyOptional.isPresent()) {
                cryptocurrencyList.add(cryptocurrencyOptional.get());
            } else {
                cryptocurrencyList.add(new Cryptocurrency());
            }
        }

        List<CryptocurrencyDto> cryptocurrencyDtoList = new ArrayList<>();

        for (Cryptocurrency cryptocurrency: cryptocurrencyList) {
            cryptocurrencyDtoList.add(CryptocurrencyMapper
                    .toDtoForRanking(cryptocurrency));
        }
        return Optional.of(cryptocurrencyDtoList);
    }

    public Optional<CryptocurrencyDto> getCryptocurrencyByCmcId(Long cmcId) {
        Optional<Cryptocurrency> cryptocurrencyOpt = Optional.ofNullable(cryptocurrencyRepository.findByCmcId(cmcId));
        if (cryptocurrencyOpt.isEmpty()) {
            return Optional.empty();
        }

        Cryptocurrency cryptocurrency = cryptocurrencyOpt.get();

        List<HistoricalData> historicalDataList = historicalDataRepository.findByCmcId(cmcId);
        List<HistoricalDataDto> historicalDataDtoList = historicalDataList.stream()
                .map(HistoricalDataMapper::toDto)
                .collect(Collectors.toList());

        CryptocurrencyDto cryptocurrencyDto = CryptocurrencyMapper.toDto(cryptocurrency, historicalDataDtoList);

        return Optional.of(cryptocurrencyDto);
    }


    //@Scheduled(fixedRate = 3600000)
    public List<Cryptocurrency> fetchAndSaveCryptocurrencies() {
        try {
            String response = restTemplate.getForObject(API_URL + "?CMC_PRO_API_KEY=" + API_KEY, String.class);

            List<Cryptocurrency> cryptocurrencies = CryptocurrencyMapper.mapJsonResponseToCryptocurrency(response);

            platformRepository.deleteAll();

            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                Platform platform = cryptocurrency.getPlatform();
                if (platform != null && platform.getId() == null) {
                    platformRepository.save(platform);
                }
            }

            cryptocurrencyRepository.deleteAll();

            cryptocurrencyRepository.saveAll(cryptocurrencies);
            return cryptocurrencies;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchAndSaveHistoricalData() {

        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
        for (long i=81;i<100;i++) {
            Cryptocurrency cryptocurrency = cryptocurrencyRepository.findById(i).get();
            cryptocurrencies.add(cryptocurrency);
        }

        for (Cryptocurrency cryptocurrency : cryptocurrencies) {
            Long cmcId = cryptocurrency.getCmcId();

            try {
                String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/historical";
                UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("CMC_PRO_API_KEY", API_KEY)
                        .queryParam("id", cmcId)
                        .queryParam("interval", "daily")
                        .queryParam("time_start", LocalDate.now().minusYears(2).toString())
                        .queryParam("time_end", LocalDate.now().toString());

                ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, String.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(response.getBody());
                    JsonNode dataNode = rootNode.path("data").path("quotes");

                    if (dataNode.isArray()) {
                        List<HistoricalData> historicalDataList = new ArrayList<>();
                        for (JsonNode node : dataNode) {
                            JsonNode usdNode = node.path("quote").path("USD");
                            HistoricalData data = HistoricalData.builder()
                                    .price(usdNode.path("price").asDouble())
                                    .volume24h(usdNode.path("volume_24h").asText())
                                    .circulatingSupply(usdNode.path("circulating_supply").asText())
                                    .marketCap(usdNode.path("market_cap").asText())
                                    .date(node.path("timestamp").asText())
                                    .cmcId(cmcId)
                                    .build();
                            historicalDataList.add(data);
                        }
                        historicalDataRepository.saveAll(historicalDataList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Optional<List<HistoricalDataDto>> getHistoricalDataByCmcId(Long cmcId) {
        List<HistoricalData> historicalDataList = historicalDataRepository.findByCmcId(cmcId);
        List<HistoricalDataDto> historicalDataDtoList = historicalDataList.stream()
                .map(HistoricalDataMapper::toDto)
                .collect(Collectors.toList());

        return Optional.of(historicalDataDtoList);
    }

    public Optional<List<HistoricalDataDto>> getSpecificDayHistoricalData(String day) {
        List<HistoricalData> historicalDataList = historicalDataRepository.findByDate(day);
        List<HistoricalDataDto> historicalDataDtoList = historicalDataList.stream()
                .map(HistoricalDataMapper::toDto)
                .collect(Collectors.toList());

        return Optional.of(historicalDataDtoList);
    }
}
