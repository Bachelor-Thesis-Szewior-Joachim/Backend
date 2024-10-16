package org.example.backend.blockchain.solana.transaction.entity.transaction.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.addressTableLookups.AddressTableLookups;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.Header;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SolanaTransactionMessage {

    private List<String> accountKeys;
    private Header header;
    private String recentBlockHash;
    private List<Instruction> instructions;
    private AddressTableLookups addressTableLookups;
}
