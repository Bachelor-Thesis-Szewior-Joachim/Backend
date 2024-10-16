package org.example.backend.blockchain.solana.accounts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccount;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;

public class SolanaAccountMapper {
    public static SolanaAccount mapAccountDtoToAccount(SolanaAccountDto accountDto) {
        return SolanaAccount.builder()
                .lamports(accountDto.getLamports())
                .slot(accountDto.getSlot())
                .owner(accountDto.getOwner())
                .data(accountDto.getData())
                .coding(accountDto.getCoding())
                .executable(accountDto.isExecutable())
                .rentEpoch(accountDto.getRentEpoch())
                .size(accountDto.getSize())
                .build();
    }

    public static SolanaAccountDto mapAccountToAccountDto(SolanaAccount account) {
        return SolanaAccountDto.builder()
                .lamports(account.getLamports())
                .slot(account.getSlot())
                .owner(account.getOwner())
                .data(account.getData())
                .coding(account.getCoding())
                .executable(account.isExecutable())
                .rentEpoch(account.getRentEpoch())
                .size(account.getSize())
                .build();

    }

    public static SolanaAccount mapToSolanaAccount(JsonNode accountInfoNode, Long slot) {
        SolanaAccount account = new SolanaAccount();

        // Set the balance (lamports), owner, and other fields
        account.setLamports(accountInfoNode.get("lamports").asLong());
        account.setOwner(accountInfoNode.get("owner").asText());
        account.setData(accountInfoNode.get("data").get(0).asText()); // First element of "data" array (Base64)
        account.setExecutable(accountInfoNode.get("executable").asBoolean());
        account.setRentEpoch(accountInfoNode.get("rentEpoch").asLong());
        account.setSlot(slot); // Slot passed separately
        account.setSize((long) accountInfoNode.get("data").get(0).asText().length()); // Size based on data length

        return account;
    }

    // Mapping from SolanaAccount to SolanaAccountDto
    public static SolanaAccountDto mapToDto(SolanaAccount solanaAccount) {
        return SolanaAccountDto.builder()
        .lamports(solanaAccount.getLamports())
        .owner(solanaAccount.getOwner())
        .rentEpoch(solanaAccount.getRentEpoch())
        .executable(solanaAccount.isExecutable())
                .build();
    }
}
