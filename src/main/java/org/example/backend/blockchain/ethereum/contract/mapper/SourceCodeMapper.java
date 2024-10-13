package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCode;
import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCodeDto;

public class SourceCodeMapper {

    public static SourceCode mapSourceCodeDtoToSourceCode(SourceCodeDto sourceCodeDto) {
        return SourceCode.builder()
                .sourceCode(sourceCodeDto.getSourceCode())
                .ABI(sourceCodeDto.getABI())
                .build();
    }

    public static SourceCodeDto mapSourceCodeToSourceCodeDto(SourceCode sourceCode) {
        return SourceCodeDto.builder()
                .sourceCode(sourceCode.getSourceCode())
                .ABI(sourceCode.getABI())
                .build();
    }
}
