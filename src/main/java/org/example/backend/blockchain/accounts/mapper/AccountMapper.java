package org.example.backend.blockchain.accounts.mapper;

import org.example.backend.blockchain.accounts.entity.Account;
import org.example.backend.blockchain.accounts.entity.AccountDto;

public class AccountMapper {
    public static Account mapAccountDtoToAccount(AccountDto accountDto) {
        return Account.builder().
                id(accountDto.getId())
                .publicKey(accountDto.getPublicKey())
                .privateKey(accountDto.getPrivateKey())
                .wallet(accountDto.getWallet())
                .client(accountDto.getClient())
                .build();
    }

    public static AccountDto mapAccountToAccountDto(Account account) {
        return AccountDto.builder().
                id(account.getId())
                .publicKey(account.getPublicKey())
                .privateKey(account.getPrivateKey())
                .wallet(account.getWallet())
                .client(account.getClient())
                .build();

    }
}
