package org.example.backend.blockchain.ethereum.block.mapper;

import org.example.backend.blockchain.ethereum.block.entity.Withdrawal;
import org.example.backend.blockchain.ethereum.block.entity.WithdrawalDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawalMapperTest {

    @Test
    public void testMapToWithdrawalDto() {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setIndex("1");
        withdrawal.setValidatorIndex("100");
        withdrawal.setAddress("0xwithdrawalAddress");
        withdrawal.setAmount("50");

        WithdrawalDto withdrawalDto = WithdrawalMapper.toDto(withdrawal);

        assertEquals(withdrawal.getIndex(), withdrawalDto.getIndex());
        assertEquals(withdrawal.getValidatorIndex(), withdrawalDto.getValidatorIndex());
        assertEquals(withdrawal.getAddress(), withdrawalDto.getAddress());
        assertEquals(withdrawal.getAmount(), withdrawalDto.getAmount());
    }

    @Test
    public void testMapToWithdrawal() {
        WithdrawalDto withdrawalDto = new WithdrawalDto();
        withdrawalDto.setIndex("1");
        withdrawalDto.setValidatorIndex("100");
        withdrawalDto.setAddress("0xwithdrawalAddress");
        withdrawalDto.setAmount("50");

        Withdrawal withdrawal = WithdrawalMapper.fromDto(withdrawalDto);

        assertEquals(withdrawalDto.getIndex(), withdrawal.getIndex());
        assertEquals(withdrawalDto.getValidatorIndex(), withdrawal.getValidatorIndex());
        assertEquals(withdrawalDto.getAddress(), withdrawal.getAddress());
        assertEquals(withdrawalDto.getAmount(), withdrawal.getAmount());
    }

    @Test
    public void testMapToWithdrawalDtoList() {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setIndex("1");
        withdrawal.setValidatorIndex("100");
        withdrawal.setAddress("0xwithdrawalAddress");
        withdrawal.setAmount("50");

        List<Withdrawal> withdrawals = Arrays.asList(withdrawal);
        List<WithdrawalDto> withdrawalDtos = WithdrawalMapper.toDtoList(withdrawals);

        assertEquals(withdrawals.size(), withdrawalDtos.size());
        assertEquals(withdrawals.get(0).getIndex(), withdrawalDtos.get(0).getIndex());
    }

    @Test
    public void testMapToWithdrawalList() {
        WithdrawalDto withdrawalDto = new WithdrawalDto();
        withdrawalDto.setIndex("1");
        withdrawalDto.setValidatorIndex("100");
        withdrawalDto.setAddress("0xwithdrawalAddress");
        withdrawalDto.setAmount("50");

        List<WithdrawalDto> withdrawalDtos = Arrays.asList(withdrawalDto);
        List<Withdrawal> withdrawals = WithdrawalMapper.fromDtoList(withdrawalDtos);

        assertEquals(withdrawalDtos.size(), withdrawals.size());
        assertEquals(withdrawalDtos.get(0).getIndex(), withdrawals.get(0).getIndex());
    }
}