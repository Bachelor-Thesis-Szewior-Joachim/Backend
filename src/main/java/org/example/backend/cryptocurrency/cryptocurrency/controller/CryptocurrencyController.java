package org.example.backend.cryptocurrency.cryptocurrency.controller;

import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.Cryptocurrency;
import org.example.backend.cryptocurrency.cryptocurrency.entity.currency.CryptocurrencyDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.historicalData.HistoricalDataDto;
import org.example.backend.cryptocurrency.cryptocurrency.entity.platform.PlatformDto;
import org.example.backend.cryptocurrency.cryptocurrency.service.CryptocurrencyService;
import org.example.backend.cryptocurrency.cryptocurrency.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cryptocurrency")
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;
    private final TokenService tokenService;

    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService, TokenService tokenService) {
        this.cryptocurrencyService = cryptocurrencyService;
        this.tokenService = tokenService;
    }

    @GetMapping("/{cmcId}")
    public ResponseEntity<CryptocurrencyDto> getCryptocurrencyByCmcId(@PathVariable Long cmcId) {
        Optional<CryptocurrencyDto> cryptocurrencyDtoOpt = cryptocurrencyService.getCryptocurrencyByCmcId(cmcId);
        if (cryptocurrencyDtoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cryptocurrencyDtoOpt.get());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<CryptocurrencyDto>> getCryptocurrencyRanking(@RequestParam Long startIndex,@RequestParam Long lastIndex) {
        Optional<List<CryptocurrencyDto>> optionalCryptocurrencyDtoList = cryptocurrencyService.getCryptocurrencyRanking(startIndex, lastIndex);

        return optionalCryptocurrencyDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/historicalData/{cmcId}")
    public ResponseEntity<List<HistoricalDataDto>> getHistoricalDataOfCryptocurrency(@PathVariable Long cmcId) {
        Optional<List<HistoricalDataDto>> optionalCryptocurrencyDtoList = cryptocurrencyService.getHistoricalDataByCmcId(cmcId);

        return optionalCryptocurrencyDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/loadCryptocurrenciesToDatabase")
    public ResponseEntity<List<Cryptocurrency>> loadCryptocurrenciesToDatabase() {
        List<Cryptocurrency> cryptocurrencies = cryptocurrencyService.fetchAndSaveCryptocurrencies();
        return ResponseEntity.ok(cryptocurrencies);
    }

    @GetMapping("/loadHistoricalData")
    public ResponseEntity<Void> loadHistoricalData() {
        cryptocurrencyService.fetchAndSaveHistoricalData();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tokens")
    public ResponseEntity<List<PlatformDto>> getAllTokens(@RequestParam Long startIndex, @RequestParam Long lastIndex) {
        Optional<List<PlatformDto>> optionalTokenList = tokenService.getAllTokens(startIndex, lastIndex);
        return optionalTokenList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
