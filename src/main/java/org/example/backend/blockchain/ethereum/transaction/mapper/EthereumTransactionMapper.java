package org.example.backend.blockchain.ethereum.transaction.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;

public class EthereumTransactionMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static EthereumTransactionDto mapTransactionToTransactionDto(EthereumTransaction transaction) {
        return EthereumTransactionDto.builder()
                .blockNumber(transaction.getBlockNumber())
                .blockHash(transaction.getBlockHash())
                .timeStamp(transaction.getTimeStamp())
                .hash(transaction.getHash())
                .nonce(transaction.getNonce())
                .transactionIndex(transaction.getTransactionIndex())
                .from(transaction.getFrom())
                .to(transaction.getTo())
                .value(transaction.getValue())
                .gas(transaction.getGas())
                .gasPrice(transaction.getGasPrice())
                .input(transaction.getInput())
                .methodId(transaction.getMethodId())
                .functionName(transaction.getFunctionName())
                .contractAddress(transaction.getContractAddress())
                .cumulativeGasUsed(transaction.getCumulativeGasUsed())
                .txReceiptStatus(transaction.getTxReceiptStatus())
                .gasUsed(transaction.getGasUsed())
                .confirmations(transaction.getConfirmations())
                .isError(transaction.getIsError())
                .build();
    }

    public static EthereumTransaction mapTransactionDtoToTransaction(EthereumTransactionDto transactionDto) {
        return EthereumTransaction.builder()
                .blockNumber(transactionDto.getBlockNumber())
                .blockHash(transactionDto.getBlockHash())
                .timeStamp(transactionDto.getTimeStamp())
                .hash(transactionDto.getHash())
                .nonce(transactionDto.getNonce())
                .transactionIndex(transactionDto.getTransactionIndex())
                .from(transactionDto.getFrom())
                .to(transactionDto.getTo())
                .value(transactionDto.getValue())
                .gas(transactionDto.getGas())
                .gasPrice(transactionDto.getGasPrice())
                .input(transactionDto.getInput())
                .methodId(transactionDto.getMethodId())
                .functionName(transactionDto.getFunctionName())
                .contractAddress(transactionDto.getContractAddress())
                .cumulativeGasUsed(transactionDto.getCumulativeGasUsed())
                .txReceiptStatus(transactionDto.getTxReceiptStatus())
                .gasUsed(transactionDto.getGasUsed())
                .confirmations(transactionDto.getConfirmations())
                .isError(transactionDto.getIsError())
                .build();
    }

    public static EthereumTransaction mapToEthereumTransaction(JsonNode transactionNode) {
        return EthereumTransaction.builder()
                .blockNumber(transactionNode.get("blockNumber").asText())
                .blockHash(transactionNode.get("blockHash").asText())
                .timeStamp(transactionNode.get("timeStamp").asText())
                .hash(transactionNode.get("hash").asText())
                .nonce(transactionNode.get("nonce").asText())
                .transactionIndex(transactionNode.get("transactionIndex").asText())
                .from(transactionNode.get("from").asText())
                .to(transactionNode.get("to").asText())
                .value(transactionNode.get("value").asText())
                .gas(transactionNode.get("gas").asText())
                .gasPrice(transactionNode.get("gasPrice").asText())
                .input(transactionNode.get("input").asText())
                .methodId(transactionNode.get("methodId").asText())
                .functionName(transactionNode.get("functionName").asText(""))
                .contractAddress(transactionNode.get("contractAddress").asText(""))
                .cumulativeGasUsed(transactionNode.get("cumulativeGasUsed").asText())
                .txReceiptStatus(transactionNode.get("txreceipt_status").asText(""))
                .gasUsed(transactionNode.get("gasUsed").asText())
                .confirmations(transactionNode.get("confirmations").asText())
                .isError(transactionNode.get("isError").asText(""))
                .build();
    }

    public static EthereumTransaction mapToEthereumTraceTransaction(JsonNode transactionNode) {
        return EthereumTransaction.builder()
                .blockNumber(transactionNode.get("blockNumber").asText())
                .timeStamp(transactionNode.get("timeStamp").asText())
                .hash(transactionNode.get("hash").asText())
                .from(transactionNode.get("from").asText())
                .to(transactionNode.get("to").asText())
                .value(transactionNode.get("value").asText())
                .contractAddress(transactionNode.get("contractAddress").asText(""))
                .input(transactionNode.get("input").asText())
                .gas(transactionNode.get("gas").asText())
                .gasUsed(transactionNode.get("gasUsed").asText())
                .traceId(transactionNode.get("traceId").asText())
                .type(transactionNode.get("type").asText())
                .isError(transactionNode.get("isError").asText())
                .errorCode(transactionNode.get("errCode").asText())
                .build();
    }




    public static EthereumTransaction mapResponseToEthereumTransaction (String jsonResponse) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode resultNode = rootNode.path("result");

        EthereumTransaction transaction = new EthereumTransaction();
        transaction.setBlockNumber(resultNode.path("blockNumber").asText(null));
        transaction.setBlockHash(resultNode.path("blockHash").asText(null));
        transaction.setHash(resultNode.path("hash").asText(null));
        transaction.setNonce(resultNode.path("nonce").asText(null));
        transaction.setTransactionIndex(resultNode.path("transactionIndex").asText(null));
        transaction.setFrom(resultNode.path("from").asText(null));
        transaction.setTo(resultNode.path("to").asText("N/A")); // Handle null to "N/A"
        transaction.setValue(resultNode.path("value").asText(null));
        transaction.setGas(resultNode.path("gas").asText(null));
        transaction.setGasPrice(resultNode.path("gasPrice").asText(null));
        transaction.setInput(resultNode.path("input").asText(null));
        transaction.setType(resultNode.path("type").asText(null));

        transaction.setMethodId(null);
        transaction.setFunctionName(null);
        transaction.setContractAddress(null);
        transaction.setCumulativeGasUsed(null);
        transaction.setTxReceiptStatus(null);
        transaction.setGasUsed(null);
        transaction.setConfirmations(null);
        transaction.setIsError(null);
        transaction.setTokenName(null);
        transaction.setTokenSymbol(null);
        transaction.setTokenDecimal(null);
        transaction.setTokenId(null);
        transaction.setTraceId(null);
        transaction.setErrorCode(null);

        return transaction;
    }
}
