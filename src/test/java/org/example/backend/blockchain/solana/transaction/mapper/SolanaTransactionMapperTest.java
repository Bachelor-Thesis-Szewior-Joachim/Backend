package org.example.backend.blockchain.solana.transaction.mapper;

import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessage;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessageDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.Header;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.HeaderDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.InstructionDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolanaTransactionMapperTest {

    @Test
    public void testToDto() {
        SolanaTransaction transaction = SolanaTransaction.builder()
                .blockTime("1234567890")
                .slot("12345")
                .signatures(List.of("signature1", "signature2"))
                .message(SolanaTransactionMessage.builder()
                        .accountKeys(List.of("key1", "key2"))
                        .header(Header.builder()
                                .numReadonlySignedAccounts(1L)
                                .numReadonlyUnsignedAccounts(2L)
                                .numRequiredSignatures(3L)
                                .build())
                        .recentBlockHash("blockhash")
                        .instructions(List.of(Instruction.builder()
                                .programIdIndex("1")
                                .accounts(List.of("account1", "account2"))
                                .data("data")
                                .stackHeight(1)
                                .build()))
                        .blockHash("blockhash")
                        .build())
                .build();

        SolanaTransactionDto dto = SolanaTransactionMapper.toDto(transaction);

        assertNotNull(dto);
        assertEquals(transaction.getBlockTime(), dto.getBlockTime());
        assertEquals(transaction.getSlot(), dto.getSlot());
        assertEquals(transaction.getSignatures(), dto.getSignatures());

        SolanaTransactionMessageDto messageDto = dto.getMessage();
        assertNotNull(messageDto);
        assertEquals(transaction.getMessage().getAccountKeys(), messageDto.getAccountKeys());
        assertEquals(transaction.getMessage().getRecentBlockHash(), messageDto.getRecentBlockHash());
        assertEquals(transaction.getMessage().getBlockHash(), messageDto.getBlockHash());

        HeaderDto headerDto = messageDto.getHeader();
        assertNotNull(headerDto);
        assertEquals(transaction.getMessage().getHeader().getNumReadonlySignedAccounts(), headerDto.getNumReadonlySignedAccounts());
        assertEquals(transaction.getMessage().getHeader().getNumReadonlyUnsignedAccounts(), headerDto.getNumReadonlyUnsignedAccounts());
        assertEquals(transaction.getMessage().getHeader().getNumRequiredSignatures(), headerDto.getNumRequiredSignatures());

        List<InstructionDto> instructionDtos = messageDto.getInstructions();
        assertNotNull(instructionDtos);
        assertEquals(1, instructionDtos.size());

        InstructionDto instructionDto = instructionDtos.get(0);
        assertEquals(transaction.getMessage().getInstructions().get(0).getProgramIdIndex(), instructionDto.getProgramIdIndex());
        assertEquals(transaction.getMessage().getInstructions().get(0).getAccounts(), instructionDto.getAccounts());
        assertEquals(transaction.getMessage().getInstructions().get(0).getData(), instructionDto.getData());
        assertEquals(transaction.getMessage().getInstructions().get(0).getStackHeight(), instructionDto.getStackHeight());
    }

    @Test
    public void testToEntity() {
        SolanaTransactionDto dto = SolanaTransactionDto.builder()
                .blockTime("1234567890")
                .slot("12345")
                .signatures(List.of("signature1", "signature2"))
                .message(SolanaTransactionMessageDto.builder()
                        .accountKeys(List.of("key1", "key2"))
                        .header(HeaderDto.builder()
                                .numReadonlySignedAccounts(1L)
                                .numReadonlyUnsignedAccounts(2L)
                                .numRequiredSignatures(3L)
                                .build())
                        .recentBlockHash("blockhash")
                        .instructions(List.of(InstructionDto.builder()
                                .programIdIndex("1")
                                .accounts(List.of("account1", "account2"))
                                .data("data")
                                .stackHeight(1)
                                .build()))
                        .blockHash("blockhash")
                        .build())
                .build();

        SolanaTransaction transaction = SolanaTransactionMapper.toEntity(dto);

        assertNotNull(transaction);
        assertEquals(dto.getBlockTime(), transaction.getBlockTime());
        assertEquals(dto.getSlot(), transaction.getSlot());
        assertEquals(dto.getSignatures(), transaction.getSignatures());

        SolanaTransactionMessage message = transaction.getMessage();
        assertNotNull(message);
        assertEquals(dto.getMessage().getAccountKeys(), message.getAccountKeys());
        assertEquals(dto.getMessage().getRecentBlockHash(), message.getRecentBlockHash());
        assertEquals(dto.getMessage().getBlockHash(), message.getBlockHash());

        Header header = message.getHeader();
        assertNotNull(header);
        assertEquals(dto.getMessage().getHeader().getNumReadonlySignedAccounts(), header.getNumReadonlySignedAccounts());
        assertEquals(dto.getMessage().getHeader().getNumReadonlyUnsignedAccounts(), header.getNumReadonlyUnsignedAccounts());
        assertEquals(dto.getMessage().getHeader().getNumRequiredSignatures(), header.getNumRequiredSignatures());

        List<Instruction> instructions = message.getInstructions();
        assertNotNull(instructions);
        assertEquals(1, instructions.size());

        Instruction instruction = instructions.get(0);
        assertEquals(dto.getMessage().getInstructions().get(0).getProgramIdIndex(), instruction.getProgramIdIndex());
        assertEquals(dto.getMessage().getInstructions().get(0).getAccounts(), instruction.getAccounts());
        assertEquals(dto.getMessage().getInstructions().get(0).getData(), instruction.getData());
        assertEquals(dto.getMessage().getInstructions().get(0).getStackHeight(), instruction.getStackHeight());
    }
}