package org.example.backend.blockchain.ethereum.contract.mapper;

import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCode;
import org.example.backend.blockchain.ethereum.contract.entity.sourceCode.SourceCodeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SourceCodeMapperTest {

    @Test
    public void testMapSourceCodeDtoToSourceCode() {
        SourceCodeDto sourceCodeDto = SourceCodeDto.builder()
                .sourceCode("sample source code")
                .ABI("sample ABI")
                .build();

        SourceCode sourceCode = SourceCodeMapper.mapSourceCodeDtoToSourceCode(sourceCodeDto);

        assertEquals(sourceCodeDto.getSourceCode(), sourceCode.getSourceCode());
        assertEquals(sourceCodeDto.getABI(), sourceCode.getABI());
    }

    @Test
    public void testMapSourceCodeToSourceCodeDto() {
        SourceCode sourceCode = SourceCode.builder()
                .sourceCode("sample source code")
                .ABI("sample ABI")
                .build();

        SourceCodeDto sourceCodeDto = SourceCodeMapper.mapSourceCodeToSourceCodeDto(sourceCode);

        assertEquals(sourceCode.getSourceCode(), sourceCodeDto.getSourceCode());
        assertEquals(sourceCode.getABI(), sourceCodeDto.getABI());
    }
}