package org.example.backend.blockchain.ethereum.contract.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContractResponseDto {
    private String message;
    private int status;
    private List<ContractDto> result;
}
