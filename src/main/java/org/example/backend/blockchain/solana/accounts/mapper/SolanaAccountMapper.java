package org.example.backend.blockchain.solana.accounts.mapper;

import org.example.backend.blockchain.solana.accounts.entity.SolanaAccount;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;

public class SolanaAccountMapper {
    public static SolanaAccount mapAccountDtoToAccount(SolanaAccountDto accountDto) {
        return SolanaAccount.builder().
                id(accountDto.getId())
                .publicKey(accountDto.getPublicKey())
                .privateKey(accountDto.getPrivateKey())
                .wallet(accountDto.getWallet())
                .client(accountDto.getClient())
                .address(accountDto.getAddress())
                .nameTag(accountDto.getNameTag())
                .balance(accountDto.getBalance())
                .percentage(accountDto.getPercentage())
                .totalCount(accountDto.getTotalCount())
                .build();
    }

    public static SolanaAccountDto mapAccountToAccountDto(SolanaAccount account) {
        return SolanaAccountDto.builder().
                id(account.getId())
                .publicKey(account.getPublicKey())
                .privateKey(account.getPrivateKey())
                .wallet(account.getWallet())
                .client(account.getClient())
                .address(account.getAddress())
                .nameTag(account.getNameTag())
                .balance(account.getBalance())
                .percentage(account.getPercentage())
                .totalCount(account.getTotalCount())
                .build();

    }
}
