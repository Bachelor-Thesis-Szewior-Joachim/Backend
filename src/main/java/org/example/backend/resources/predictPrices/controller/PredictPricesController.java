package org.example.backend.resources.predictPrices.controller;

import org.example.backend.resources.predictPrices.service.PredictPricesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PredictPricesController {

    private final PredictPricesService predictionService;

    public PredictPricesController(PredictPricesService predictionService) {
        this.predictionService = predictionService;
    }

    /**
     * Get predictions for Ethereum, Bitcoin, and Solana models in one request
     * @return Predictions from all models
     */
    @PostMapping("/predict")
    public ResponseEntity<Map<String, Object>> getAllPredictions(@RequestBody Map<String, String> request) {
        try {
            String dateInput = request.get("dateInput");
            Map<String, Object> predictions = predictionService.getAllPredictions(dateInput);
            return ResponseEntity.ok(predictions);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
