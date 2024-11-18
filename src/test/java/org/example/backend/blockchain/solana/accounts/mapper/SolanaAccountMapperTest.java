package org.example.backend.blockchain.solana.accounts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccount;
import org.example.backend.blockchain.solana.accounts.entity.SolanaAccountDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaAccountMapperTest {

    @Test
    public void testMapAccountDtoToAccount() {
        SolanaAccountDto accountDto = SolanaAccountDto.builder()
                .lamports(1000L)
                .slot(12345L)
                .owner("ownerAddress")
                .data("data")
                .coding("coding")
                .executable(true)
                .rentEpoch("rentEpoch")
                .space(2048L)
                .build();

        SolanaAccount account = SolanaAccountMapper.mapAccountDtoToAccount(accountDto);

        assertEquals(accountDto.getLamports(), account.getLamports());
        assertEquals(accountDto.getSlot(), account.getSlot());
        assertEquals(accountDto.getOwner(), account.getOwner());
        assertEquals(accountDto.getData(), account.getData());
        assertEquals(accountDto.getCoding(), account.getCoding());
        assertEquals(accountDto.isExecutable(), account.isExecutable());
        assertEquals(accountDto.getRentEpoch(), account.getRentEpoch());
        assertEquals(accountDto.getSpace(), account.getSpace());
    }

    @Test
    public void testMapAccountToAccountDto() {
        SolanaAccount account = SolanaAccount.builder()
                .lamports(1000L)
                .slot(12345L)
                .owner("ownerAddress")
                .data("data")
                .coding("coding")
                .executable(true)
                .rentEpoch("rentEpoch")
                .space(2048L)
                .build();

        SolanaAccountDto accountDto = SolanaAccountMapper.mapAccountToAccountDto(account);

        assertEquals(account.getLamports(), accountDto.getLamports());
        assertEquals(account.getSlot(), accountDto.getSlot());
        assertEquals(account.getOwner(), accountDto.getOwner());
        assertEquals(account.getData(), accountDto.getData());
        assertEquals(account.getCoding(), accountDto.getCoding());
        assertEquals(account.isExecutable(), accountDto.isExecutable());
        assertEquals(account.getRentEpoch(), accountDto.getRentEpoch());
        assertEquals(account.getSpace(), accountDto.getSpace());
    }

    @Test
    public void testMapToSolanaAccount() throws Exception {
        String jsonResponse = "{ \"result\": { \"context\": { \"slot\": 12345 }, \"value\": { \"lamports\": 1000, \"owner\": \"ownerAddress\", \"data\": \"data\", \"executable\": true, \"rentEpoch\": \"rentEpoch\", \"space\": 2048 } } }";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        SolanaAccount account = SolanaAccountMapper.mapToSolanaAccount(jsonResponse);

        assertEquals(1000L, account.getLamports());
        assertEquals(12345L, account.getSlot());
        assertEquals("ownerAddress", account.getOwner());
        assertEquals("data", account.getData());
        assertEquals(true, account.isExecutable());
        assertEquals("rentEpoch", account.getRentEpoch());
        assertEquals(2048L, account.getSpace());
    }
}