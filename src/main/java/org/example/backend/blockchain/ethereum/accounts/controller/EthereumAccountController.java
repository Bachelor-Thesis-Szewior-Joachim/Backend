package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.accounts.service.EthereumAccountService;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ethereum/account")
public class EthereumAccountController {

    private final EthereumAccountService ethereumAccountService;

    public EthereumAccountController(EthereumAccountService ethereumAccountService) {
        this.ethereumAccountService = ethereumAccountService;
    }

    @GetMapping("/data/{address}")
    public Optional<EthereumAccountDto> GetEtherBalanceAndTransactionHistory(@PathVariable String address) {
        return ethereumAccountService.getEtherBalanceAndTransactionHistory(address);
    }

    @GetMapping("/tokenBalance/{address}/{contractAddress}")
    public ResponseEntity<Double> getTokenBalance(@PathVariable String address, @PathVariable String contractAddress) {
        Optional<Double> ethereumAccountDtoOptional = Optional.ofNullable(ethereumAccountService.getTokenBalance(address, contractAddress));

        if (ethereumAccountDtoOptional.isPresent()) {
            return ResponseEntity.ok(ethereumAccountDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/erc20transfers/{address}")
    public ResponseEntity<List<EthereumTransactionDto>> getERC20TokenTransfers(@PathVariable String address,
                                                                                   @RequestParam(defaultValue = "0") int startBlock,
                                                                                   @RequestParam(defaultValue = "99999999") int endBlock,
                                                                                   @RequestParam(defaultValue = "asc") String sort) {

        Optional<List<EthereumTransactionDto>> optionalEthereumTransactionDtos = ethereumAccountService.getERC20TokenTransfers(address, startBlock, endBlock, sort);

        if (optionalEthereumTransactionDtos.isPresent()) {
            return ResponseEntity.ok(optionalEthereumTransactionDtos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/erc721transfers/{address}")
    public ResponseEntity<List<EthereumTransactionDto>> getERC721TokenTransfers(@PathVariable String address,
                                          @RequestParam(defaultValue = "0") int startBlock,
                                          @RequestParam(defaultValue = "99999999") int endBlock,
                                          @RequestParam(defaultValue = "asc") String sort) {

        Optional<List<EthereumTransactionDto>> optionalEthereumTransactionDtos = ethereumAccountService
                .getERC721TokenTransfers(address, startBlock, endBlock, sort);

        if (optionalEthereumTransactionDtos.isPresent()) {
            return ResponseEntity.ok(optionalEthereumTransactionDtos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/blocksMinedByAddress/{address}")
    public String getBlocksMinedByAddress(@PathVariable String address) {
        return ethereumAccountService.getBlocksMinedByAddress(address);
    }
}
