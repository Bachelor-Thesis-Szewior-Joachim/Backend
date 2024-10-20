package org.example.backend.blockchain.solana.transaction.mapper;

import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransactionDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessage;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessageDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.Header;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.HeaderDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.InstructionDto;

import java.util.List;
import java.util.stream.Collectors;

public class SolanaTransactionMapper {

    // Convert from Entity to DTO
    public static SolanaTransactionDto toDto(SolanaTransaction transaction) {
        if (transaction == null) {
            return null;
        }

        return SolanaTransactionDto.builder()
                .blockTime(transaction.getBlockTime())
                .slot(transaction.getSlot())
                .signatures(transaction.getSignatures())
                .message(toDto(transaction.getMessage()))
                .build();
    }

    public static SolanaTransactionMessageDto toDto(SolanaTransactionMessage message) {
        if (message == null) {
            return null;
        }

        return SolanaTransactionMessageDto.builder()
                .accountKeys(message.getAccountKeys())
                .header(toDto(message.getHeader()))
                .recentBlockHash(message.getRecentBlockHash())
                .instructions(toDto(message.getInstructions()))
                .blockHash(message.getBlockHash())
                .build();
    }

    public static List<InstructionDto> toDto(List<Instruction> instructions) {
        if (instructions == null) {
            return null;
        }

        return instructions.stream()
                .map(SolanaTransactionMapper::toDto)
                .collect(Collectors.toList());
    }

    public static InstructionDto toDto(Instruction instruction) {
        if (instruction == null) {
            return null;
        }

        return InstructionDto.builder()
                .programIdIndex(instruction.getProgramIdIndex())
                .accounts(instruction.getAccounts())
                .data(instruction.getData())
                .stackHeight(instruction.getStackHeight())
                .build();
    }

    public static HeaderDto toDto(Header header) {
        if (header == null) {
            return null;
        }

        return HeaderDto.builder()
                .numReadonlySignedAccounts(header.getNumReadonlySignedAccounts())
                .numReadonlyUnsignedAccounts(header.getNumReadonlyUnsignedAccounts())
                .numRequiredSignatures(header.getNumRequiredSignatures())
                .build();
    }

    // Convert from DTO to Entity
    public static SolanaTransaction toEntity(SolanaTransactionDto transactionDto) {
        if (transactionDto == null) {
            return null;
        }

        return SolanaTransaction.builder()
                .blockTime(transactionDto.getBlockTime())
                .slot(transactionDto.getSlot())
                .signatures(transactionDto.getSignatures())
                .message(toEntity(transactionDto.getMessage()))
                .build();
    }

    public static SolanaTransactionMessage toEntity(SolanaTransactionMessageDto messageDto) {
        if (messageDto == null) {
            return null;
        }

        return SolanaTransactionMessage.builder()
                .accountKeys(messageDto.getAccountKeys())
                .header(toEntity(messageDto.getHeader()))
                .recentBlockHash(messageDto.getRecentBlockHash())
                .instructions(toEntity(messageDto.getInstructions()))
                .blockHash(messageDto.getBlockHash())
                .build();
    }

    public static List<Instruction> toEntity(List<InstructionDto> instructionDtos) {
        if (instructionDtos == null) {
            return null;
        }

        return instructionDtos.stream()
                .map(SolanaTransactionMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static Instruction toEntity(InstructionDto instructionDto) {
        if (instructionDto == null) {
            return null;
        }

        return Instruction.builder()
                .programIdIndex(instructionDto.getProgramIdIndex())
                .accounts(instructionDto.getAccounts())
                .data(instructionDto.getData())
                .stackHeight(instructionDto.getStackHeight())
                .build();
    }

    public static Header toEntity(HeaderDto headerDto) {
        if (headerDto == null) {
            return null;
        }

        return Header.builder()
                .numReadonlySignedAccounts(headerDto.getNumReadonlySignedAccounts())
                .numReadonlyUnsignedAccounts(headerDto.getNumReadonlyUnsignedAccounts())
                .numRequiredSignatures(headerDto.getNumRequiredSignatures())
                .build();
    }
}
