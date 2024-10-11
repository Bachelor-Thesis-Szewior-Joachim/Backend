package org.example.backend.blockchain.bitcoin.wallet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.backend.blockchain.bitcoin.wallet.service.WalletService;

@RestController
@RequestMapping("/bitcoin/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // Endpoint to get details of a specific wallet
    @GetMapping("/{walletName}")
    public ResponseEntity<String> getWalletDetails(@PathVariable String walletName) {
        String walletDetails = walletService.getWalletDetails(walletName);
        return new ResponseEntity<>(walletDetails, HttpStatus.OK);
    }

    // Endpoint to get details of a specific HD wallet
    @GetMapping("/hd/{walletName}")
    public ResponseEntity<String> getHDWalletDetails(@PathVariable String walletName) {
        String hdWalletDetails = walletService.getHDWalletDetails(walletName);
        return new ResponseEntity<>(hdWalletDetails, HttpStatus.OK);
    }

    // Endpoint to get details of a specific chain in an HD wallet
    @GetMapping("/hd/{walletName}/chains/{chainIndex}")
    public ResponseEntity<String> getHDChainDetails(@PathVariable String walletName, @PathVariable String chainIndex) {
        String hdChainDetails = walletService.getHDChainDetails(walletName, chainIndex);
        return new ResponseEntity<>(hdChainDetails, HttpStatus.OK);
    }

    // Endpoint to get addresses associated with a specific HD wallet chain
    @GetMapping("/hd/{walletName}/chains/{chainIndex}/address")
    public ResponseEntity<String> getHDAddressDetails(@PathVariable String walletName, @PathVariable String chainIndex) {
        String hdAddressDetails = walletService.getHDAddressDetails(walletName, chainIndex);
        return new ResponseEntity<>(hdAddressDetails, HttpStatus.OK);
    }
}
