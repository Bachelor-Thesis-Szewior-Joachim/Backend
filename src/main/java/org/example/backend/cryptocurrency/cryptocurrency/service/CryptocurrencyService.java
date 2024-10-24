package org.example.backend.cryptocurrency.cryptocurrency.service;

import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.cryptocurrency.CryptocurrencyDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalData;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.CryptocurrencyMapper;
import org.example.backend.cryptocurrency.cryptocurrency.mapper.HistoricalDataMapper;
import org.example.backend.cryptocurrency.cryptocurrency.repository.CryptocurrencyRepository;
import org.example.backend.cryptocurrency.cryptocurrency.repository.HistoricalDataRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CryptocurrencyService {

    private static final String API_KEY = "d42f0690-3288-4f73-8230-da9ac5135859";
    private static final String API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private final RestTemplate restTemplate;
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final HistoricalDataRepository historicalDataRepository;


    public CryptocurrencyService(RestTemplate restTemplate, CryptocurrencyRepository cryptocurrencyRepository, HistoricalDataRepository historicalDataRepository) {
        this.restTemplate = restTemplate;
        this.cryptocurrencyRepository = cryptocurrencyRepository;
        this.historicalDataRepository = historicalDataRepository;
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
                    .mapCryptocurrencyToCryptocurrencyDto(cryptocurrency));
        }
        return Optional.of(cryptocurrencyDtoList);
    }

    @Scheduled(fixedRate = 3600000)
    public void fetchAndSaveCryptocurrencies() {
        try {
            String response = restTemplate.getForObject(API_URL + "?CMC_PRO_API_KEY=" + API_KEY, String.class);

            List<Cryptocurrency> cryptocurrencies = CryptocurrencyMapper.mapJsonResponseToCryptocurrency(response);

            cryptocurrencyRepository.deleteAll();

            cryptocurrencyRepository.saveAll(cryptocurrencies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<List<HistoricalDataDto>> getHistoricalData(Long cmcId) {

        List<HistoricalDataDto> historicalDataList = new ArrayList<>();
        Cryptocurrency cryptocurrency = cryptocurrencyRepository.findByCmcId(cmcId);
        CryptocurrencyDto cryptocurrencyDto = CryptocurrencyMapper.mapCryptocurrencyToCryptocurrencyDto(cryptocurrency);
        historicalDataList = cryptocurrencyDto.getPricesAllTime();
        return Optional.of(historicalDataList);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight every day
    public void fetchAndSaveHistoricalData() {
        try {
            List<Cryptocurrency> cryptocurrencies = (List<Cryptocurrency>) cryptocurrencyRepository.findAll();

            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
                Long cmcId = cryptocurrency.getCmcId();

                String apiUrlWithParams = API_URL + "?id=" + cmcId + "&CMC_PRO_API_KEY=" + API_KEY;

                String response = restTemplate.getForObject(apiUrlWithParams, String.class);

                List<HistoricalData> historicalDataList = HistoricalDataMapper.mapJsonResponseToHistoricalData(response, cryptocurrency);

                historicalDataRepository.saveAll(historicalDataList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
