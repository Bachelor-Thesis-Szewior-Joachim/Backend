package org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class InstructionDto {

    private String programIdIndex;
    private List<String> accounts;
    private String data;
    private Integer stackHeight;
}
