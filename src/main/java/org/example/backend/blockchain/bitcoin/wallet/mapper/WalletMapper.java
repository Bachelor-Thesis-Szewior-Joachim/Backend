package org.example.backend.blockchain.bitcoin.wallet.mapper;

import org.example.backend.blockchain.bitcoin.wallet.entity.Wallet;
import org.example.backend.blockchain.bitcoin.wallet.entity.WalletDto;

public class WalletMapper {

    public static WalletDto toWalletDto(Wallet wallet) {
        return WalletDto.builder()
                .name(wallet.getName())
                .token(wallet.getToken())
                .addresses(wallet.getAddresses())
                .build();
    }

    // Converts from WalletDto to Wallet
    public static Wallet toWallet(WalletDto walletDto) {
        return Wallet.builder()
        .name(walletDto.getName())
                .token(walletDto.getToken())
        .addresses(walletDto.getAddresses())
                .build();
    }
}
