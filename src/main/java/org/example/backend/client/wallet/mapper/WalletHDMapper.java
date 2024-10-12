package org.example.backend.client.wallet.mapper;

import org.example.backend.client.wallet.entity.WalletHD;
import org.example.backend.client.wallet.entity.WalletHDDto;

public class WalletHDMapper {

    public static WalletHDDto mapWalletHDToWalletHDDto(WalletHD walletHD) {
        return WalletHDDto.builder()
                .name(walletHD.getName())
                .token(walletHD.getToken())
                .extendedPublicKey(walletHD.getExtendedPublicKey())
                .subchainIndexes(walletHD.getSubchainIndexes())
                .build();
    }

    // Converts from WalletHDDto to WalletHD
    public static WalletHD mapWalletHDDtoToWalletHD(WalletHDDto walletHDDto) {
        return WalletHD.builder()
                .name(walletHDDto.getName())
                .token(walletHDDto.getToken())
                .extendedPublicKey(walletHDDto.getExtendedPublicKey())
            .subchainIndexes(walletHDDto.getSubchainIndexes())
                .build();

    }
}
