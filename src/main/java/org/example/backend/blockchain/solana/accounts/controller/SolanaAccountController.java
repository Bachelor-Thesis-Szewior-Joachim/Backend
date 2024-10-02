package org.example.backend.blockchain.solana.accounts.controller;

import org.example.backend.blockchain.solana.accounts.service.SolanaAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solana/account")
public class SolanaAccountController {

    private SolanaAccountService solanaAccountService;

    @GetMapping("/{address}")
    public String getAccountInfo(@PathVariable String address) {
        try {
            return solanaAccountService.getAccountInfo(address);
        } catch (Exception e) {
            return "Error fetching account info: " + e.getMessage();
        }
    }

}
