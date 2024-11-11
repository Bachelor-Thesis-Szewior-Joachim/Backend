package org.example.backend.resources.converter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    public final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping
    public ResponseEntity<String> convert(@RequestParam String baseCurrency, @RequestParam String targetCurrency, @RequestParam double amount) {
        String value = converterService.convert(baseCurrency, targetCurrency, amount);
        return ResponseEntity.ok(value);
    }
}
