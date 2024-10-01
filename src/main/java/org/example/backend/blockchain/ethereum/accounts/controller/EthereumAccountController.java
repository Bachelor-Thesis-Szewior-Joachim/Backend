package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.data.bitcoin.BitcoinService;
import org.example.backend.blockchain.data.ethereum.EthereumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/ethereum")
public class EthereumAccountController {

    private final EthereumService ethereumService;


    public EthereumAccountController(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @GetMapping("/client-version")
    public ResponseEntity<?> getClientVersion() throws ExecutionException, InterruptedException {
        String example = ethereumService.getClientVersion();
            return new ResponseEntity<>(example, HttpStatus.OK);
    }

}
