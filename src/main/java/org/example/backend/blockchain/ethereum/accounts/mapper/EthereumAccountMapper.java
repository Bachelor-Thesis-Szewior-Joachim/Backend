package org.example.backend.blockchain.ethereum.accounts.mapper;

import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccount;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;

public class EthereumAccountMapper {
    public static EthereumAccount mapAccountDtoToAccount(EthereumAccountDto accountDto) {
        return EthereumAccount.builder().
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

    public static EthereumAccountDto mapAccountToAccountDto(EthereumAccount account) {
        return EthereumAccountDto.builder().
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
