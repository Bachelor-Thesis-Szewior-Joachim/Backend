package org.example.backend.blockchain.ethereum.accounts.service;

import org.example.backend.blockchain.ethereum.accounts.repository.EthereumAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class EthereumAccountService {

    private final EthereumAccountRepository accountRepository;

    public EthereumAccountService(EthereumAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
