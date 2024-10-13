package org.example.backend.blockchain.ethereum.transaction.controller;


import okhttp3.Response;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.example.backend.blockchain.ethereum.transaction.service.EthereumTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ethereum/transaction")
public class EthereumTransactionController {

    private final EthereumTransactionService ethereumTransactionService;

    public EthereumTransactionController(EthereumTransactionService ethereumTransactionService) {
        this.ethereumTransactionService = ethereumTransactionService;
    }

    //Returns the list of transactions performed by an address, with optional pagination.
    @GetMapping("/normal/{address}")
    public ResponseEntity<List<EthereumTransactionDto>> getNormalTransactions(@PathVariable String address,
                                                                              @RequestParam(defaultValue = "0") int startBlock,
                                                                              @RequestParam(defaultValue = "99999999") int endBlock,
                                                                              @RequestParam(defaultValue = "asc") String sort) {
        Optional<List<EthereumTransactionDto>> optionalEthereumTransactionDtoList =
                ethereumTransactionService.getNormalTransactions(address, startBlock, endBlock, sort);
        if (optionalEthereumTransactionDtoList.isPresent()) {
            return ResponseEntity.ok(optionalEthereumTransactionDtoList.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Returns the list of internal transactions performed by an address, with optional pagination
    @GetMapping("/internal/{address}")
    public ResponseEntity<List<EthereumTransactionDto>> getInternalTransactions(@PathVariable String address,
                                          @RequestParam(defaultValue = "0") int startBlock,
                                          @RequestParam(defaultValue = "99999999") int endBlock,
                                          @RequestParam(defaultValue = "asc") String sort) {
        Optional<List<EthereumTransactionDto>> optionalEthereumTransactionDtoList =
                ethereumTransactionService.getInternalTransactions(address, startBlock, endBlock, sort);
        if (optionalEthereumTransactionDtoList.isPresent()) {
            return ResponseEntity.ok(optionalEthereumTransactionDtoList.get());
        } else {
            return ResponseEntity.notFound().build();
        }    }

    @GetMapping("/estimateGas")
    public ResponseEntity<String> estimateGas(@RequestParam String to, @RequestParam String value, @RequestParam String data) {
        Optional<String> estimateGasOptional = ethereumTransactionService.estimateGas(to, value, data);

        if (estimateGasOptional.isPresent()) {
            return ResponseEntity.ok(estimateGasOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contractStatus/{txHash}")
    public ResponseEntity<Map<String, String>> getContractExecutionStatus(@PathVariable String txHash) {
        Optional<Map<String, String>> optionalMap = ethereumTransactionService.getContractExecutionStatus(txHash);

        if (optionalMap.isPresent()) {
            return ResponseEntity.ok(optionalMap.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/receiptStatus/{txHash}")
    public ResponseEntity<Map<String, String>> getTransactionReceiptStatus(@PathVariable String txHash) {
        Optional<Map<String, String>> optionalTransactionReceipt = ethereumTransactionService.getTransactionReceiptStatus(txHash);

        if (optionalTransactionReceipt.isPresent()) {
            return ResponseEntity.ok(optionalTransactionReceipt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
