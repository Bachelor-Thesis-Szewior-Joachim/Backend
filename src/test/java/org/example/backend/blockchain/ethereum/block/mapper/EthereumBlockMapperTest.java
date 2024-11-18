package org.example.backend.blockchain.ethereum.block.mapper;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlock;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;
import org.example.backend.blockchain.ethereum.block.entity.Withdrawal;
import org.example.backend.blockchain.ethereum.block.entity.WithdrawalDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EthereumBlockMapperTest {

    @Test
    public void testMapToEthereumBlockDto() {
        EthereumBlock ethereumBlock = new EthereumBlock();
        ethereumBlock.setNumber("12345L");
        ethereumBlock.setHash("0xabc123");
        ethereumBlock.setMiner("0xminer123");

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setIndex("1");
        withdrawal.setValidatorIndex("100");
        withdrawal.setAddress("0xwithdrawalAddress");
        withdrawal.setAmount("50");

        List<Withdrawal> withdrawals = Arrays.asList(withdrawal);
        ethereumBlock.setWithdrawals(withdrawals);

        EthereumBlockDto ethereumBlockDto = EthereumBlockMapper.toDto(ethereumBlock);

        assertEquals(ethereumBlock.getNumber(), ethereumBlockDto.getNumber());
        assertEquals(ethereumBlock.getHash(), ethereumBlockDto.getHash());
        assertEquals(ethereumBlock.getMiner(), ethereumBlockDto.getMiner());
        assertEquals(ethereumBlock.getWithdrawals().size(), ethereumBlockDto.getWithdrawals().size());
        assertEquals(ethereumBlock.getWithdrawals().get(0).getIndex(), ethereumBlockDto.getWithdrawals().get(0).getIndex());
    }

    @Test
    public void testMapToEthereumBlock() {
        EthereumBlockDto ethereumBlockDto = new EthereumBlockDto();
        ethereumBlockDto.setNumber("12345L");
        ethereumBlockDto.setHash("0xabc123");
        ethereumBlockDto.setMiner("0xminer123");

        WithdrawalDto withdrawalDto = new WithdrawalDto();
        withdrawalDto.setIndex("1");
        withdrawalDto.setValidatorIndex("100");
        withdrawalDto.setAddress("0xwithdrawalAddress");
        withdrawalDto.setAmount("50");

        List<WithdrawalDto> withdrawalDtos = Arrays.asList(withdrawalDto);
        ethereumBlockDto.setWithdrawals(withdrawalDtos);

        EthereumBlock ethereumBlock = EthereumBlockMapper.fromDto(ethereumBlockDto);

        assertEquals(ethereumBlockDto.getNumber(), ethereumBlock.getNumber());
        assertEquals(ethereumBlockDto.getHash(), ethereumBlock.getHash());
        assertEquals(ethereumBlockDto.getMiner(), ethereumBlock.getMiner());
        assertEquals(ethereumBlockDto.getWithdrawals().size(), ethereumBlock.getWithdrawals().size());
        assertEquals(ethereumBlockDto.getWithdrawals().get(0).getIndex(), ethereumBlock.getWithdrawals().get(0).getIndex());
    }
}