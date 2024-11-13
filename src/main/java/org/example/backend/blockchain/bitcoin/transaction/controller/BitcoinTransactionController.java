package org.example.backend.blockchain.bitcoin.transaction.controller;

import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;
import org.example.backend.blockchain.bitcoin.transaction.service.BitcoinTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bitcoin/transaction")
public class BitcoinTransactionController {

    private final BitcoinTransactionService bitcoinTransactionService;

    public BitcoinTransactionController(BitcoinTransactionService bitcoinTransactionService) {
        this.bitcoinTransactionService = bitcoinTransactionService;
    }

    @GetMapping("/data/{hash}")
    public ResponseEntity<BitcoinTransactionDto> getTransaction(@PathVariable String hash) {

        Optional<BitcoinTransactionDto> bitcoinTransactionDtoOptional =
                Optional.ofNullable(bitcoinTransactionService.getTransactionByHash(hash));

        if (bitcoinTransactionDtoOptional.isPresent()) {
            return ResponseEntity.ok(bitcoinTransactionDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get transaction input details
    @GetMapping("/{txHash}/inputs")
    public ResponseEntity<List<BitcoinTransactionInputDto>> getTransactionInput(@PathVariable String txHash) {
        List<BitcoinTransactionInputDto> inputDtoList = bitcoinTransactionService.getTransactionInput(txHash);
        if (inputDtoList != null) {
            return ResponseEntity.ok(inputDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get transaction output details
    @GetMapping("/{txHash}/outputs")
    public ResponseEntity<List<BitcoinTransactionOutputDto>> getTransactionOutput(@PathVariable String txHash) {
        List<BitcoinTransactionOutputDto> outputDto = bitcoinTransactionService.getTransactionOutput(txHash);
        if (outputDto != null) {
            return ResponseEntity.ok(outputDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get transaction confidence
    @GetMapping("/{txHash}/confidence")
    public ResponseEntity<Float> getTransactionConfidence(@PathVariable String txHash) {
        return ResponseEntity.ok(bitcoinTransactionService.getTransactionConfidence(txHash));
    }
}
