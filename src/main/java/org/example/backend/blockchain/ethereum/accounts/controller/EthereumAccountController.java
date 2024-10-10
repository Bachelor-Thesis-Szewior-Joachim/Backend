package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.accounts.service.EthereumAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ethereum/account")
public class EthereumAccountController {

    private final EthereumAccountService ethereumAccountService;

    @Autowired
    public EthereumAccountController(EthereumAccountService ethereumAccountService) {
        this.ethereumAccountService = ethereumAccountService;
    }

    @GetMapping("/data/{address}")
    public String getEtherData(@PathVariable String address) {
        return ethereumAccountService.getEtherBalanceAndTransactionHistory(address);
    }

    @GetMapping("/tokenBalance/{address}/{contractAddress}")
    public ResponseEntity<EthereumAccountDto> getTokenBalance(@PathVariable String address, @PathVariable String contractAddress) {
        Optional<EthereumAccountDto> ethereumAccountDtoOptional = Optional.ofNullable(ethereumAccountService.getTokenBalance(address, contractAddress));

        if (ethereumAccountDtoOptional.isPresent()) {
            return ResponseEntity.ok(ethereumAccountDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/erc20transfers/{address}")
    public String getERC20TokenTransfers(@PathVariable String address,
                                         @RequestParam(defaultValue = "0") int startBlock,
                                         @RequestParam(defaultValue = "99999999") int endBlock,
                                         @RequestParam(defaultValue = "asc") String sort) {
        return ethereumAccountService.getERC20TokenTransfers(address, startBlock, endBlock, sort);
    }

    @GetMapping("/erc721transfers/{address}")
    public String getERC721TokenTransfers(@PathVariable String address,
                                          @RequestParam(defaultValue = "0") int startBlock,
                                          @RequestParam(defaultValue = "99999999") int endBlock,
                                          @RequestParam(defaultValue = "asc") String sort) {
        return ethereumAccountService.getERC721TokenTransfers(address, startBlock, endBlock, sort);
    }

    @GetMapping("/blocksMinedByAddress/{address}")
    public String getBlocksMinedByAddress(@PathVariable String address) {
        return ethereumAccountService.getBlocksMinedByAddress(address);
    }
}
