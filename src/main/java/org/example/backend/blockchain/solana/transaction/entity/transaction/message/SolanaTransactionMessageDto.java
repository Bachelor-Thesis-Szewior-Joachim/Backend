package org.example.backend.blockchain.solana.transaction.entity.transaction.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.addressTableLookups.AddressTableLookupsDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.HeaderDto;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.InstructionDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaTransactionMessageDto {

    private List<String> accountKeys;
    private HeaderDto header;
    private String recentBlockHash;
    private List<InstructionDto> instructions;
    private AddressTableLookupsDto addressTableLookups;
}
