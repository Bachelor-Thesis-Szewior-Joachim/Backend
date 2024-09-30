package org.example.backend.blockchain.ethereum.accounts.controller;

import org.example.backend.blockchain.data.bitcoin.BitcoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountss")
public class EthereumAccountController {

    private final BitcoinService bitcoinService;


    public EthereumAccountController(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }
//    @GetMapping("/{blockchain}/{address}")
//    public ResponseEntity<?> getAccountInfo(@PathVariable String blockchain, @PathVariable String address) {
//        //String example = bitcoinService.getAccountByAddress(address);
//        return new ResponseEntity<>("", HttpStatus.OK);
//    }


}
