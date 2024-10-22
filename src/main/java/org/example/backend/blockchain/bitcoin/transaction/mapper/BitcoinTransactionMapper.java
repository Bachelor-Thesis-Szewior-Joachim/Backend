package org.example.backend.blockchain.bitcoin.transaction.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.bitcoin.transaction.entity.input.BitcoinTransactionInputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.output.BitcoinTransactionOutputDto;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransaction;
import org.example.backend.blockchain.bitcoin.transaction.entity.transaction.BitcoinTransactionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BitcoinTransactionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static BitcoinTransactionDto mapTransactionToTransactionDto(BitcoinTransaction bitcoinTransaction) {
        return BitcoinTransactionDto.builder()
                .hash(bitcoinTransaction.getHash())
                .blockHeight(bitcoinTransaction.getBlockHeight())
                .blockHash(bitcoinTransaction.getBlockHash())
                .blockIndex(bitcoinTransaction.getBlockIndex())
                .total(bitcoinTransaction.getTotal())
                .fees(bitcoinTransaction.getFees())
                .size(bitcoinTransaction.getSize())
                .vsize(bitcoinTransaction.getVsize())
                .preference(bitcoinTransaction.getPreference())
                .confirmed(bitcoinTransaction.getConfirmed())
                .received(bitcoinTransaction.getReceived())
                .ver(bitcoinTransaction.getVer())
                .doubleSpend(bitcoinTransaction.isDoubleSpend())
                .vinSz(bitcoinTransaction.getVinSz())
                .voutSz(bitcoinTransaction.getVoutSz())
                .dataProtocol(bitcoinTransaction.getDataProtocol())
                .confirmations(bitcoinTransaction.getConfirmations())
                .confidence(bitcoinTransaction.getConfidence())
                .inputsDto(
                        bitcoinTransaction.getInputs().stream()
                                .map(input -> BitcoinTransactionInputMapper
                                        .mapFromBitcoinTransactionInputToBitcoinTransactionInputDto(input))
                                .collect(Collectors.toList())
                                )
                .outputsDto(
                        bitcoinTransaction.getOutputs().stream()
                                .map(output -> BitcoinTransactionOutputMapper
                                        .mapBitcoinTransactionOutputToBitcoinTransactionOutputDto(output))
                                .collect(Collectors.toList()))
                .addresses(bitcoinTransaction.getAddresses()) // Mapping addresses (List<List<String>>)
                .build();
    }

    public static BitcoinTransaction mapTransactionDtoToTransaction(BitcoinTransactionDto bitcoinTransactionDto) {
        return BitcoinTransaction.builder()
                .hash(bitcoinTransactionDto.getHash())
                .blockHeight(bitcoinTransactionDto.getBlockHeight())
                .blockHash(bitcoinTransactionDto.getBlockHash())
                .blockIndex(bitcoinTransactionDto.getBlockIndex())
                .total(bitcoinTransactionDto.getTotal())
                .fees(bitcoinTransactionDto.getFees())
                .size(bitcoinTransactionDto.getSize())
                .vsize(bitcoinTransactionDto.getVsize())
                .preference(bitcoinTransactionDto.getPreference())
                .confirmed(bitcoinTransactionDto.getConfirmed())
                .received(bitcoinTransactionDto.getReceived())
                .ver(bitcoinTransactionDto.getVer())
                .doubleSpend(bitcoinTransactionDto.isDoubleSpend())
                .vinSz(bitcoinTransactionDto.getVinSz())
                .voutSz(bitcoinTransactionDto.getVoutSz())
                .dataProtocol(bitcoinTransactionDto.getDataProtocol())
                .confirmations(bitcoinTransactionDto.getConfirmations())
                .confidence(bitcoinTransactionDto.getConfidence())
                .inputs(
                        bitcoinTransactionDto.getInputsDto().stream()
                                .map(input -> BitcoinTransactionInputMapper
                                        .mapFromBitcoinTransactionInputDtoToBitcoinTransactionInput(input))
                                .collect(Collectors.toList()))
                .outputs(
                        bitcoinTransactionDto.getOutputsDto().stream()
                                .map(output -> BitcoinTransactionOutputMapper
                                        .mapBitcoinTransactionOutputDtoToBitcoinTransactionOutput(output))
                                .collect(Collectors.toList()))
                .addresses(bitcoinTransactionDto.getAddresses()) // Mapping addresses (List<List<String>>)
                .build();
    }


    public static BitcoinTransactionDto jsonMap(String json) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        BitcoinTransactionDto transaction = new BitcoinTransactionDto();

        transaction.setBlockHash(rootNode.path("block_hash").asText(null));
        transaction.setBlockHeight(rootNode.path("block_height").asText(null));
        transaction.setBlockIndex(rootNode.path("block_index").asText(null));
        transaction.setHash(rootNode.path("hash").asText());
        transaction.setAddresses(objectMapper.convertValue(
                rootNode.path("addresses"), objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)));
        transaction.setTotal(rootNode.path("total").asText());
        transaction.setFees(rootNode.path("fees").asText());
        transaction.setSize(rootNode.path("size").asText());
        transaction.setVsize(rootNode.path("vsize").asText());
        transaction.setPreference(rootNode.path("preference").asText());
        transaction.setConfirmed(rootNode.path("confirmed").asText());
        transaction.setReceived(rootNode.path("received").asText());
        transaction.setVer(rootNode.path("ver").asText());
        transaction.setDoubleSpend(rootNode.path("double_spend").asBoolean());
        transaction.setVinSz(rootNode.path("vin_sz").asText(null));
        transaction.setVoutSz(rootNode.path("vout_sz").asText(null));
        transaction.setDataProtocol(rootNode.path("data_protocol").asText(null));
        transaction.setConfirmations(rootNode.path("confirmations").asText());
        transaction.setConfidence(rootNode.path("confidence").floatValue());

        List<BitcoinTransactionInputDto> inputs = new ArrayList<>();
        JsonNode inputsNode = rootNode.path("inputs");
        if (inputsNode.isArray()) {
            for (JsonNode inputNode : inputsNode) {
                BitcoinTransactionInputDto input = new BitcoinTransactionInputDto();
                input.setOutputIndex(inputNode.path("output_index").asText(null));
                input.setScriptType(inputNode.path("script_type").asText(null));
                input.setScript(inputNode.path("script").asText());
                input.setSequence(inputNode.path("sequence").asText());
                input.setAge(inputNode.path("age").asText());
                inputs.add(input);
            }
        }
        transaction.setInputsDto(inputs);

        List<BitcoinTransactionOutputDto> outputs = new ArrayList<>();
        JsonNode outputsNode = rootNode.path("outputs");
        if (outputsNode.isArray()) {
            for (JsonNode outputNode : outputsNode) {
                BitcoinTransactionOutputDto output = new BitcoinTransactionOutputDto();
                output.setValue(outputNode.path("value").asText());
                output.setScript(outputNode.path("script").asText());
                output.setAddresses(objectMapper.convertValue(
                        outputNode.path("addresses"), objectMapper.getTypeFactory().constructCollectionType(List.class, String.class)));
                output.setScriptType(outputNode.path("script_type").asText(null));
                output.setDataHex(outputNode.path("data_hex").asText(null));
                outputs.add(output);
            }
        }
        transaction.setOutputsDto(outputs);

        return transaction;
    }
}
