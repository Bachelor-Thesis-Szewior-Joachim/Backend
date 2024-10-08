package org.example.backend.blockchain.ethereum.transaction.controller;


import org.example.backend.blockchain.ethereum.transaction.service.EthereumTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ethereum/transaction")
public class EthereumTransactionController {

    private final EthereumTransactionService ethereumTransactionService;

    public EthereumTransactionController(EthereumTransactionService ethereumTransactionService) {
        this.ethereumTransactionService = ethereumTransactionService;
    }

    @GetMapping("/normal/{address}")
    public String getNormalTransactions(@PathVariable String address,
                                        @RequestParam(defaultValue = "0") int startBlock,
                                        @RequestParam(defaultValue = "99999999") int endBlock,
                                        @RequestParam(defaultValue = "asc") String sort) {
        return ethereumTransactionService.getNormalTransactions(address, startBlock, endBlock, sort);
    }

    @GetMapping("/internal/{address}")
    public String getInternalTransactions(@PathVariable String address,
                                          @RequestParam(defaultValue = "0") int startBlock,
                                          @RequestParam(defaultValue = "99999999") int endBlock,
                                          @RequestParam(defaultValue = "asc") String sort) {
        return ethereumTransactionService.getInternalTransactions(address, startBlock, endBlock, sort);
    }

    @GetMapping("/estimateGas")
    public String estimateGas(@RequestParam String to, @RequestParam String value, @RequestParam String data) {
        return ethereumTransactionService.estimateGas(to, value, data);
    }

    @GetMapping("/contractStatus/{txHash}")
    public String getContractExecutionStatus(@PathVariable String txHash) {
        return ethereumTransactionService.getContractExecutionStatus(txHash);
    }

    @GetMapping("/receiptStatus/{txHash}")
    public String getTransactionReceiptStatus(@PathVariable String txHash) {
        return ethereumTransactionService.getTransactionReceiptStatus(txHash);
    }
}
