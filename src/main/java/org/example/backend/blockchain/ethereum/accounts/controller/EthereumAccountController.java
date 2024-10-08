package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.ethereum.accounts.service.EthereumAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String getTokenBalance(@PathVariable String address, @PathVariable String contractAddress) {
        return ethereumAccountService.getTokenBalance(address, contractAddress);
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
}
