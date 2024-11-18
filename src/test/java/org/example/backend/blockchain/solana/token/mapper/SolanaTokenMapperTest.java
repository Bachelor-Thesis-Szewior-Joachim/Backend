package org.example.backend.blockchain.solana.token.mapper;

import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccount;
import org.example.backend.blockchain.solana.token.entity.token.SolanaTokenAccountDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolanaTokenMapperTest {

    @Test
    public void testToDto() {
        SolanaTokenAccount entity = SolanaTokenAccount.builder()
                .pubkey("pubkey")
                .executable(true)
                .lamports(1000L)
                .owner("owner")
                .rentEpoch("rentEpoch")
                .space(200)
                .program("program")
                .dataSpace(300)
                .isNative(true)
                .mint("mint")
                .accountOwner("accountOwner")
                .state("state")
                .amount("1000")
                .decimals(2)
                .uiAmount(10.0)
                .uiAmountString("10.0")
                .type("type")
                .build();

        SolanaTokenAccountDto dto = SolanaTokenMapper.toDto(entity);

        assertEquals(entity.getPubkey(), dto.getPubkey());
        assertEquals(entity.isExecutable(), dto.isExecutable());
        assertEquals(entity.getLamports(), dto.getLamports());
        assertEquals(entity.getOwner(), dto.getOwner());
        assertEquals(entity.getRentEpoch(), dto.getRentEpoch());
        assertEquals(entity.getSpace(), dto.getSpace());
        assertEquals(entity.getProgram(), dto.getProgram());
        assertEquals(entity.getDataSpace(), dto.getDataSpace());
        assertEquals(entity.isNative(), dto.isNative());
        assertEquals(entity.getMint(), dto.getMint());
        assertEquals(entity.getAccountOwner(), dto.getAccountOwner());
        assertEquals(entity.getState(), dto.getState());
        assertEquals(entity.getAmount(), dto.getAmount());
        assertEquals(entity.getDecimals(), dto.getDecimals());
        assertEquals(entity.getUiAmount(), dto.getUiAmount());
        assertEquals(entity.getUiAmountString(), dto.getUiAmountString());
        assertEquals(entity.getType(), dto.getType());
    }

    @Test
    public void testToEntity() {
        SolanaTokenAccountDto dto = SolanaTokenAccountDto.builder()
                .pubkey("pubkey")
                .executable(true)
                .lamports(1000L)
                .owner("owner")
                .rentEpoch("rentEpoch")
                .space(200)
                .program("program")
                .dataSpace(300)
                .isNative(true)
                .mint("mint")
                .accountOwner("accountOwner")
                .state("state")
                .amount("1000")
                .decimals(2)
                .uiAmount(10.0)
                .uiAmountString("10.0")
                .type("type")
                .build();

        SolanaTokenAccount entity = SolanaTokenMapper.toEntity(dto);

        assertEquals(dto.getPubkey(), entity.getPubkey());
        assertEquals(dto.isExecutable(), entity.isExecutable());
        assertEquals(dto.getLamports(), entity.getLamports());
        assertEquals(dto.getOwner(), entity.getOwner());
        assertEquals(dto.getRentEpoch(), entity.getRentEpoch());
        assertEquals(dto.getSpace(), entity.getSpace());
        assertEquals(dto.getProgram(), entity.getProgram());
        assertEquals(dto.getDataSpace(), entity.getDataSpace());
        assertEquals(dto.isNative(), entity.isNative());
        assertEquals(dto.getMint(), entity.getMint());
        assertEquals(dto.getAccountOwner(), entity.getAccountOwner());
        assertEquals(dto.getState(), entity.getState());
        assertEquals(dto.getAmount(), entity.getAmount());
        assertEquals(dto.getDecimals(), entity.getDecimals());
        assertEquals(dto.getUiAmount(), entity.getUiAmount());
        assertEquals(dto.getUiAmountString(), entity.getUiAmountString());
        assertEquals(dto.getType(), entity.getType());
    }

    @Test
    public void testMapJsonToSolanaTokenAccount() throws Exception {
        String jsonResponse = "{ \"result\": { \"value\": [ { \"pubkey\": \"pubkey\", \"account\": { \"executable\": true, \"lamports\": 1000, \"owner\": \"owner\", \"rentEpoch\": \"rentEpoch\", \"space\": 200, \"data\": { \"program\": \"program\", \"space\": 300, \"parsed\": { \"info\": { \"isNative\": true, \"mint\": \"mint\", \"owner\": \"accountOwner\", \"state\": \"state\", \"tokenAmount\": { \"amount\": \"1000\", \"decimals\": 2, \"uiAmount\": 10.0, \"uiAmountString\": \"10.0\" } }, \"type\": \"type\" } } } } ] } }";

        SolanaTokenAccount entity = SolanaTokenMapper.mapJsonToSolanaTokenAccount(jsonResponse);

        assertEquals("pubkey", entity.getPubkey());
        assertEquals(true, entity.isExecutable());
        assertEquals(1000L, entity.getLamports());
        assertEquals("owner", entity.getOwner());
        assertEquals("rentEpoch", entity.getRentEpoch());
        assertEquals(200, entity.getSpace());
        assertEquals("program", entity.getProgram());
        assertEquals(300, entity.getDataSpace());
        assertEquals(true, entity.isNative());
        assertEquals("mint", entity.getMint());
        assertEquals("accountOwner", entity.getAccountOwner());
        assertEquals("state", entity.getState());
        assertEquals("1000", entity.getAmount());
        assertEquals(2, entity.getDecimals());
        assertEquals(10.0, entity.getUiAmount());
        assertEquals("10.0", entity.getUiAmountString());
        assertEquals("type", entity.getType());
    }
}