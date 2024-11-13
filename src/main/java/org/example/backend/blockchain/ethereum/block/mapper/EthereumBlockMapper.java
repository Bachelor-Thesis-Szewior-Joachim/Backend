package org.example.backend.blockchain.ethereum.block.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlock;
import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;
import org.example.backend.blockchain.ethereum.block.entity.WithdrawalDto;

import java.util.ArrayList;
import java.util.List;

public class EthereumBlockMapper {

    // Map from EthereumBlock to EthereumBlockDto
    public static EthereumBlockDto toDto(EthereumBlock block) {
        EthereumBlockDto dto = new EthereumBlockDto();
        dto.setBaseFeePerGas(block.getBaseFeePerGas());
        dto.setBlobGasUsed(block.getBlobGasUsed());
        dto.setDifficulty(block.getDifficulty());
        dto.setExcessBlobGas(block.getExcessBlobGas());
        dto.setExtraData(block.getExtraData());
        dto.setGasLimit(block.getGasLimit());
        dto.setGasUsed(block.getGasUsed());
        dto.setHash(block.getHash());
        dto.setLogsBloom(block.getLogsBloom());
        dto.setMiner(block.getMiner());
        dto.setMixHash(block.getMixHash());
        dto.setNonce(block.getNonce());
        dto.setNumber(block.getNumber());
        dto.setParentBeaconBlockRoot(block.getParentBeaconBlockRoot());
        dto.setParentHash(block.getParentHash());
        dto.setReceiptsRoot(block.getReceiptsRoot());
        dto.setSha3Uncles(block.getSha3Uncles());
        dto.setSize(block.getSize());
        dto.setStateRoot(block.getStateRoot());
        dto.setTimestamp(block.getTimestamp());
        dto.setTotalDifficulty(block.getTotalDifficulty());
        dto.setTransactions(block.getTransactions());
        dto.setTransactionsRoot(block.getTransactionsRoot());
        dto.setWithdrawals(WithdrawalMapper.toDtoList(block.getWithdrawals()));
        dto.setWithdrawalsRoot(block.getWithdrawalsRoot());
        return dto;
    }

    public static EthereumBlock fromDto(EthereumBlockDto dto) {
        EthereumBlock block = new EthereumBlock();
        block.setBaseFeePerGas(dto.getBaseFeePerGas());
        block.setBlobGasUsed(dto.getBlobGasUsed());
        block.setDifficulty(dto.getDifficulty());
        block.setExcessBlobGas(dto.getExcessBlobGas());
        block.setExtraData(dto.getExtraData());
        block.setGasLimit(dto.getGasLimit());
        block.setGasUsed(dto.getGasUsed());
        block.setHash(dto.getHash());
        block.setLogsBloom(dto.getLogsBloom());
        block.setMiner(dto.getMiner());
        block.setMixHash(dto.getMixHash());
        block.setNonce(dto.getNonce());
        block.setNumber(dto.getNumber());
        block.setParentBeaconBlockRoot(dto.getParentBeaconBlockRoot());
        block.setParentHash(dto.getParentHash());
        block.setReceiptsRoot(dto.getReceiptsRoot());
        block.setSha3Uncles(dto.getSha3Uncles());
        block.setSize(dto.getSize());
        block.setStateRoot(dto.getStateRoot());
        block.setTimestamp(dto.getTimestamp());
        block.setTotalDifficulty(dto.getTotalDifficulty());
        block.setTransactions(dto.getTransactions());
        block.setTransactionsRoot(dto.getTransactionsRoot());
        block.setWithdrawals(WithdrawalMapper.fromDtoList(dto.getWithdrawals()));
        block.setWithdrawalsRoot(dto.getWithdrawalsRoot());
        return block;
    }

    public static EthereumBlockDto mapJsonToEthereumBlockDto(JsonNode blockNode) {
        EthereumBlockDto blockDto = new EthereumBlockDto();

        blockDto.setBaseFeePerGas(blockNode.get("baseFeePerGas").asText());
        blockDto.setBlobGasUsed(blockNode.get("blobGasUsed").asText());
        blockDto.setDifficulty(blockNode.get("difficulty").asText());
        blockDto.setExcessBlobGas(blockNode.get("excessBlobGas").asText());
        blockDto.setExtraData(blockNode.get("extraData").asText());
        blockDto.setGasLimit(blockNode.get("gasLimit").asText());
        blockDto.setGasUsed(blockNode.get("gasUsed").asText());
        blockDto.setHash(blockNode.get("hash").asText());
        blockDto.setLogsBloom(blockNode.get("logsBloom").asText());
        blockDto.setMiner(blockNode.get("miner").asText());
        blockDto.setMixHash(blockNode.get("mixHash").asText());
        blockDto.setNonce(blockNode.get("nonce").asText());
        blockDto.setNumber(blockNode.get("number").asText());
        blockDto.setParentBeaconBlockRoot(blockNode.get("parentBeaconBlockRoot").asText());
        blockDto.setParentHash(blockNode.get("parentHash").asText());
        blockDto.setReceiptsRoot(blockNode.get("receiptsRoot").asText());
        blockDto.setSha3Uncles(blockNode.get("sha3Uncles").asText());
        blockDto.setSize(blockNode.get("size").asText());
        blockDto.setStateRoot(blockNode.get("stateRoot").asText());
        blockDto.setTimestamp(blockNode.get("timestamp").asText());
        blockDto.setTotalDifficulty(blockNode.get("totalDifficulty").asText());
        blockDto.setTransactionsRoot(blockNode.get("transactionsRoot").asText());
        blockDto.setWithdrawalsRoot(blockNode.get("withdrawalsRoot").asText());

        List<String> transactions = new ArrayList<>();
        JsonNode transactionsNode = blockNode.get("transactions");
        if (transactionsNode != null && transactionsNode.isArray()) {
            for (JsonNode transactionNode : transactionsNode) {
                transactions.add(transactionNode.asText());
            }
        }
        blockDto.setTransactions(transactions);

        List<WithdrawalDto> withdrawals = new ArrayList<>();
        JsonNode withdrawalsNode = blockNode.get("withdrawals");
        if (withdrawalsNode != null && withdrawalsNode.isArray()) {
            for (JsonNode withdrawalNode : withdrawalsNode) {
                WithdrawalDto withdrawalDto = new WithdrawalDto();
                withdrawalDto.setIndex(withdrawalNode.get("index").asText());
                withdrawalDto.setValidatorIndex(withdrawalNode.get("validatorIndex").asText());
                withdrawalDto.setAddress(withdrawalNode.get("address").asText());
                withdrawalDto.setAmount(withdrawalNode.get("amount").asText());
                withdrawals.add(withdrawalDto);
            }
        }
        blockDto.setWithdrawals(withdrawals);

        return blockDto;
    }

    public static List<EthereumBlockDto> mapMinedBlocks(JsonNode jsonNode) {
        List<EthereumBlockDto> blocks = new ArrayList<>();
        if (jsonNode != null && jsonNode.isArray()) {
            for (JsonNode blockNode : jsonNode) {
                EthereumBlockDto blockDto = new EthereumBlockDto();
                blockDto.setNumber(blockNode.get("blockNumber").asText());
                blockDto.setTimestamp(blockNode.get("timeStamp").asText()); // corrected field name
                blockDto.setBlockReward(blockNode.get("blockReward").asText());
                blocks.add(blockDto);
            }
        }
        return blocks;
    }
}
