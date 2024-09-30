package org.example.backend.blockchain.solana.accounts.service;

import org.example.backend.blockchain.solana.accounts.repository.SolanaAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class SolanaAccountService {

    private final SolanaAccountRepository accountRepository;

    public SolanaAccountService(SolanaAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
