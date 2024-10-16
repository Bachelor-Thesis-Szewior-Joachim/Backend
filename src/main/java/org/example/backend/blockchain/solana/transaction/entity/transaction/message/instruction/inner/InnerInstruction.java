package org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.inner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class InnerInstruction {

    private Long index;
    private List<Instruction> instructions;
}
