package org.example.backend.cryptocurrency.cryptocurrency.controller;

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

    @GetMapping("/ranking")
    public ResponseEntity<List<CryptocurrencyDto>> getCryptocurrencyRanking(@RequestParam Long startIndex,@RequestParam Long lastIndex) {
        Optional<List<CryptocurrencyDto>> optionalCryptocurrencyDtoList = cryptocurrencyService.getCryptocurrencyRanking(startIndex, lastIndex);

        return optionalCryptocurrencyDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ranking/{cmcId}")
    public ResponseEntity<List<HistoricalDataDto>> getHistoricalDataOfCryptocurrency(@PathVariable Long cmcId) {
        Optional<List<HistoricalDataDto>> optionalCryptocurrencyDtoList = cryptocurrencyService.getHistoricalData(cmcId);

        return optionalCryptocurrencyDtoList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/loadCryptocurrenciesToDatabase")
    public ResponseEntity<Void> loadCryptocurrenciesToDatabase() {
        cryptocurrencyService.fetchAndSaveCryptocurrencies();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tokens")
    public ResponseEntity<List<PlatformDto>> getAllTokens(@RequestParam Long startIndex, @RequestParam Long lastIndex) {
        Optional<List<PlatformDto>> optionalTokenList = tokenService.getAllTokens(startIndex, lastIndex);
        return optionalTokenList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
