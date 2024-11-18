package org.example.backend.blockchain.ethereum.contract.entity.sourceCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SourceCodeDto {
    private String sourceCode;
    private String ABI;

    public SourceCodeDto() {

    }
}
