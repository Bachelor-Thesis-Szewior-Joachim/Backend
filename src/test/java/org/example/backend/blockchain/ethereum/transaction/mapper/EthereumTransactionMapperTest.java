package org.example.backend.blockchain.ethereum.transaction.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EthereumTransactionMapperTest {

    @Test
    public void testMapTransactionToTransactionDto() {
        EthereumTransaction transaction = EthereumTransaction.builder()
                .blockNumber("12345")
                .blockHash("0xabc")
                .timeStamp("2023-01-01T00:00:00Z")
                .hash("0xdef")
                .nonce("1")
                .transactionIndex("0")
                .from("0x123")
                .to("0x456")
                .value("1000")
                .gas("21000")
                .gasPrice("50")
                .input("0x789")
                .methodId("0x1")
                .functionName("transfer")
                .contractAddress("0x789")
                .cumulativeGasUsed("21000")
                .txReceiptStatus("1")
                .gasUsed("21000")
                .confirmations("10")
                .isError("0")
                .build();

        EthereumTransactionDto dto = EthereumTransactionMapper.mapTransactionToTransactionDto(transaction);

        assertEquals(transaction.getBlockNumber(), dto.getBlockNumber());
        assertEquals(transaction.getBlockHash(), dto.getBlockHash());
        assertEquals(transaction.getTimeStamp(), dto.getTimeStamp());
        assertEquals(transaction.getHash(), dto.getHash());
        assertEquals(transaction.getNonce(), dto.getNonce());
        assertEquals(transaction.getTransactionIndex(), dto.getTransactionIndex());
        assertEquals(transaction.getFrom(), dto.getFrom());
        assertEquals(transaction.getTo(), dto.getTo());
        assertEquals(transaction.getValue(), dto.getValue());
        assertEquals(transaction.getGas(), dto.getGas());
        assertEquals(transaction.getGasPrice(), dto.getGasPrice());
        assertEquals(transaction.getInput(), dto.getInput());
        assertEquals(transaction.getMethodId(), dto.getMethodId());
        assertEquals(transaction.getFunctionName(), dto.getFunctionName());
        assertEquals(transaction.getContractAddress(), dto.getContractAddress());
        assertEquals(transaction.getCumulativeGasUsed(), dto.getCumulativeGasUsed());
        assertEquals(transaction.getTxReceiptStatus(), dto.getTxReceiptStatus());
        assertEquals(transaction.getGasUsed(), dto.getGasUsed());
        assertEquals(transaction.getConfirmations(), dto.getConfirmations());
        assertEquals(transaction.getIsError(), dto.getIsError());
    }

    @Test
    public void testMapTransactionDtoToTransaction() {
        EthereumTransactionDto dto = EthereumTransactionDto.builder()
                .blockNumber("12345")
                .blockHash("0xabc")
                .timeStamp("2023-01-01T00:00:00Z")
                .hash("0xdef")
                .nonce("1")
                .transactionIndex("0")
                .from("0x123")
                .to("0x456")
                .value("1000")
                .gas("21000")
                .gasPrice("50")
                .input("0x789")
                .methodId("0x1")
                .functionName("transfer")
                .contractAddress("0x789")
                .cumulativeGasUsed("21000")
                .txReceiptStatus("1")
                .gasUsed("21000")
                .confirmations("10")
                .isError("0")
                .build();

        EthereumTransaction transaction = EthereumTransactionMapper.mapTransactionDtoToTransaction(dto);

        assertEquals(dto.getBlockNumber(), transaction.getBlockNumber());
        assertEquals(dto.getBlockHash(), transaction.getBlockHash());
        assertEquals(dto.getTimeStamp(), transaction.getTimeStamp());
        assertEquals(dto.getHash(), transaction.getHash());
        assertEquals(dto.getNonce(), transaction.getNonce());
        assertEquals(dto.getTransactionIndex(), transaction.getTransactionIndex());
        assertEquals(dto.getFrom(), transaction.getFrom());
        assertEquals(dto.getTo(), transaction.getTo());
        assertEquals(dto.getValue(), transaction.getValue());
        assertEquals(dto.getGas(), transaction.getGas());
        assertEquals(dto.getGasPrice(), transaction.getGasPrice());
        assertEquals(dto.getInput(), transaction.getInput());
        assertEquals(dto.getMethodId(), transaction.getMethodId());
        assertEquals(dto.getFunctionName(), transaction.getFunctionName());
        assertEquals(dto.getContractAddress(), transaction.getContractAddress());
        assertEquals(dto.getCumulativeGasUsed(), transaction.getCumulativeGasUsed());
        assertEquals(dto.getTxReceiptStatus(), transaction.getTxReceiptStatus());
        assertEquals(dto.getGasUsed(), transaction.getGasUsed());
        assertEquals(dto.getConfirmations(), transaction.getConfirmations());
        assertEquals(dto.getIsError(), transaction.getIsError());
    }

    @Test
    public void testMapToEthereumTransaction() throws Exception {
        String json = "{ \"blockNumber\": \"12345\", \"blockHash\": \"0xabc\", \"timeStamp\": \"2023-01-01T00:00:00Z\", \"hash\": \"0xdef\", \"nonce\": \"1\", \"transactionIndex\": \"0\", \"from\": \"0x123\", \"to\": \"0x456\", \"value\": \"1000\", \"gas\": \"21000\", \"gasPrice\": \"50\", \"input\": \"0x789\", \"methodId\": \"0x1\", \"functionName\": \"transfer\", \"contractAddress\": \"0x789\", \"cumulativeGasUsed\": \"21000\", \"txreceipt_status\": \"1\", \"gasUsed\": \"21000\", \"confirmations\": \"10\", \"isError\": \"0\" }";
        JsonNode transactionNode = new ObjectMapper().readTree(json);

        EthereumTransaction transaction = EthereumTransactionMapper.mapToEthereumTransaction(transactionNode);

        assertEquals("12345", transaction.getBlockNumber());
        assertEquals("0xabc", transaction.getBlockHash());
        assertEquals("2023-01-01T00:00:00Z", transaction.getTimeStamp());
        assertEquals("0xdef", transaction.getHash());
        assertEquals("1", transaction.getNonce());
        assertEquals("0", transaction.getTransactionIndex());
        assertEquals("0x123", transaction.getFrom());
        assertEquals("0x456", transaction.getTo());
        assertEquals("1000", transaction.getValue());
        assertEquals("21000", transaction.getGas());
        assertEquals("50", transaction.getGasPrice());
        assertEquals("0x789", transaction.getInput());
        assertEquals("0x1", transaction.getMethodId());
        assertEquals("transfer", transaction.getFunctionName());
        assertEquals("0x789", transaction.getContractAddress());
        assertEquals("21000", transaction.getCumulativeGasUsed());
        assertEquals("1", transaction.getTxReceiptStatus());
        assertEquals("21000", transaction.getGasUsed());
        assertEquals("10", transaction.getConfirmations());
        assertEquals("0", transaction.getIsError());
    }

    @Test
    public void testMapToEthereumTraceTransaction() throws Exception {
        String json = "{ \"blockNumber\": \"12345\", \"timeStamp\": \"2023-01-01T00:00:00Z\", \"hash\": \"0xdef\", \"from\": \"0x123\", \"to\": \"0x456\", \"value\": \"1000\", \"contractAddress\": \"0x789\", \"input\": \"0x789\", \"gas\": \"21000\", \"gasUsed\": \"21000\", \"traceId\": \"1\", \"type\": \"call\", \"isError\": \"0\", \"errCode\": \"\" }";
        JsonNode transactionNode = new ObjectMapper().readTree(json);

        EthereumTransaction transaction = EthereumTransactionMapper.mapToEthereumTraceTransaction(transactionNode);

        assertEquals("12345", transaction.getBlockNumber());
        assertEquals("2023-01-01T00:00:00Z", transaction.getTimeStamp());
        assertEquals("0xdef", transaction.getHash());
        assertEquals("0x123", transaction.getFrom());
        assertEquals("0x456", transaction.getTo());
        assertEquals("1000", transaction.getValue());
        assertEquals("0x789", transaction.getContractAddress());
        assertEquals("0x789", transaction.getInput());
        assertEquals("21000", transaction.getGas());
        assertEquals("21000", transaction.getGasUsed());
        assertEquals("1", transaction.getTraceId());
        assertEquals("call", transaction.getType());
        assertEquals("0", transaction.getIsError());
        assertEquals("", transaction.getErrorCode());
    }

    @Test
    public void testMapResponseToEthereumTransaction() throws Exception {
        String jsonResponse = "{ \"result\": { \"blockNumber\": \"12345\", \"blockHash\": \"0xabc\", \"hash\": \"0xdef\", \"nonce\": \"1\", \"transactionIndex\": \"0\", \"from\": \"0x123\", \"to\": \"0x456\", \"value\": \"1000\", \"gas\": \"21000\", \"gasPrice\": \"50\", \"input\": \"0x789\", \"type\": \"0\" } }";

        EthereumTransaction transaction = EthereumTransactionMapper.mapResponseToEthereumTransaction(jsonResponse);

        assertEquals("12345", transaction.getBlockNumber());
        assertEquals("0xabc", transaction.getBlockHash());
        assertEquals("0xdef", transaction.getHash());
        assertEquals("1", transaction.getNonce());
        assertEquals("0", transaction.getTransactionIndex());
        assertEquals("0x123", transaction.getFrom());
        assertEquals("0x456", transaction.getTo());
        assertEquals("1000", transaction.getValue());
        assertEquals("21000", transaction.getGas());
        assertEquals("50", transaction.getGasPrice());
        assertEquals("0x789", transaction.getInput());
        assertEquals("0", transaction.getType());
    }
}