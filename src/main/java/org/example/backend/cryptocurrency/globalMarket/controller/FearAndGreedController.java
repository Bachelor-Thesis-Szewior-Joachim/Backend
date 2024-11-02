package org.example.backend.cryptocurrency.globalMarket.controller;

import org.example.backend.cryptocurrency.globalMarket.entity.FearAndGreedDto;
import org.example.backend.cryptocurrency.globalMarket.service.FearAndGreedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/global-data")
public class FearAndGreedController {

    private final FearAndGreedService fearAndGreedService;

    @Autowired
    public FearAndGreedController(FearAndGreedService fearAndGreedService) {
        this.fearAndGreedService = fearAndGreedService;
    }

    @GetMapping("/latest")
    public ResponseEntity<List<FearAndGreedDto>> getLatestFearAndGreed() {
        Optional<List<FearAndGreedDto>> fearAndGreedDto = fearAndGreedService.getLatestFearAndGreed();

        return fearAndGreedDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
