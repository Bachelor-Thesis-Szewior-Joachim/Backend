package org.example.backend.blockchain.ethereum.block.mapper;

import org.example.backend.blockchain.ethereum.block.entity.Withdrawal;
import org.example.backend.blockchain.ethereum.block.entity.WithdrawalDto;

import java.util.List;
import java.util.stream.Collectors;

public class WithdrawalMapper {

    // Map from Withdrawal to WithdrawalDto
    public static WithdrawalDto toDto(Withdrawal withdrawal) {
        WithdrawalDto dto = new WithdrawalDto();
        dto.setIndex(withdrawal.getIndex());
        dto.setValidatorIndex(withdrawal.getValidatorIndex());
        dto.setAddress(withdrawal.getAddress());
        dto.setAmount(withdrawal.getAmount());
        return dto;
    }

    // Map from WithdrawalDto to Withdrawal
    public static Withdrawal fromDto(WithdrawalDto dto) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setIndex(dto.getIndex());
        withdrawal.setValidatorIndex(dto.getValidatorIndex());
        withdrawal.setAddress(dto.getAddress());
        withdrawal.setAmount(dto.getAmount());
        return withdrawal;
    }

    // Map a list of Withdrawal to a list of WithdrawalDto
    public static List<WithdrawalDto> toDtoList(List<Withdrawal> withdrawals) {
        return withdrawals.stream().map(WithdrawalMapper::toDto).collect(Collectors.toList());
    }

    // Map a list of WithdrawalDto to a list of Withdrawal
    public static List<Withdrawal> fromDtoList(List<WithdrawalDto> withdrawalDtos) {
        return withdrawalDtos.stream().map(WithdrawalMapper::fromDto).collect(Collectors.toList());
    }
}
