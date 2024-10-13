package org.example.backend.blockchain.ethereum.accounts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccount;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;

import org.example.backend.blockchain.ethereum.transaction.mapper.EthereumTransactionMapper;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EthereumAccountMapper {
    public static EthereumAccount mapAccountDtoToAccount(EthereumAccountDto accountDto) {
        return EthereumAccount.builder()
                .balance(accountDto.getBalance())
                .transactions(accountDto.getTransactions().stream()
                        .map(ethereumTransaction -> EthereumTransactionMapper
                                .mapTransactionDtoToTransaction(ethereumTransaction))
                        .collect(Collectors.toList()))
                .build();
    }

    public static EthereumAccountDto mapAccountToAccountDto(EthereumAccount account) {
        return EthereumAccountDto.builder()
                .balance(account.getBalance())
                .transactions(account.getTransactions().stream()
                        .map(ethereumTransaction -> EthereumTransactionMapper
                                .mapTransactionToTransactionDto(ethereumTransaction))
                        .collect(Collectors.toList()))
                .build();

    }

    public static EthereumAccount mapToEthereumAccount(JsonNode balanceNode, JsonNode transactionHistoryNode) throws IOException {
        EthereumAccount account = new EthereumAccount();

        // Set the balance from the response
        account.setBalance(balanceNode.get("result").asText());

        // Parse the transaction history
        List<EthereumTransaction> transactions = new ArrayList<>();
        JsonNode transactionArray = transactionHistoryNode.get("result");

        if (transactionArray.isArray()) {
            for (JsonNode transactionNode : transactionArray) {
                EthereumTransaction transaction = new EthereumTransaction();

                transaction.setBlockNumber(transactionNode.get("blockNumber").asText());
                transaction.setBlockHash(transactionNode.get("blockHash").asText());
                transaction.setTimeStamp(transactionNode.get("timeStamp").asText());
                transaction.setHash(transactionNode.get("hash").asText());
                transaction.setNonce(transactionNode.get("nonce").asText());
                transaction.setTransactionIndex(transactionNode.get("transactionIndex").asText());
                transaction.setFrom(transactionNode.get("from").asText());
                transaction.setTo(transactionNode.get("to").asText());
                transaction.setValue(transactionNode.get("value").asText());
                transaction.setGas(transactionNode.get("gas").asText());
                transaction.setGasPrice(transactionNode.get("gasPrice").asText());
                transaction.setInput(transactionNode.get("input").asText());
                transaction.setMethodId(transactionNode.get("methodId").asText());
                transaction.setFunctionName(transactionNode.get("functionName").asText());
                transaction.setContractAddress(transactionNode.get("contractAddress").asText());
                transaction.setCumulativeGasUsed(transactionNode.get("cumulativeGasUsed").asText());
                transaction.setTxReceiptStatus(transactionNode.get("txreceipt_status").asText());
                transaction.setGasUsed(transactionNode.get("gasUsed").asText());
                transaction.setConfirmations(transactionNode.get("confirmations").asText());
                transaction.setIsError(transactionNode.get("isError").asText());

                transactions.add(transaction);
            }
        }

        account.setTransactions(transactions);
        return account;
    }

    public static EthereumTransactionDto mapToEthereumTransactionDto(JsonNode transactionNode) {
        EthereumTransactionDto transaction = new EthereumTransactionDto();

        transaction.setBlockNumber(transactionNode.get("blockNumber").asText());
        transaction.setTimeStamp(transactionNode.get("timeStamp").asText());
        transaction.setHash(transactionNode.get("hash").asText());
        transaction.setNonce(transactionNode.get("nonce").asText());
        transaction.setBlockHash(transactionNode.get("blockHash").asText());
        transaction.setFrom(transactionNode.get("from").asText());
        transaction.setTo(transactionNode.get("to").asText());
        transaction.setContractAddress(transactionNode.get("contractAddress").asText());
        transaction.setValue(transactionNode.get("value").asText());
        transaction.setTokenName(transactionNode.get("tokenName").asText());
        transaction.setTokenSymbol(transactionNode.get("tokenSymbol").asText());
        transaction.setTokenDecimal(transactionNode.get("tokenDecimal").asText());
        transaction.setTransactionIndex(transactionNode.get("transactionIndex").asText());
        transaction.setGas(transactionNode.get("gas").asText());
        transaction.setGasPrice(transactionNode.get("gasPrice").asText());
        transaction.setGasUsed(transactionNode.get("gasUsed").asText());
        transaction.setCumulativeGasUsed(transactionNode.get("cumulativeGasUsed").asText());
        transaction.setConfirmations(transactionNode.get("confirmations").asText());

        return transaction;
    }

    public static EthereumTransactionDto mapToEthereumTransactionDtoWithTokenIdAndNoValue(JsonNode transactionNode) {
        EthereumTransactionDto transaction = new EthereumTransactionDto();

        transaction.setBlockNumber(transactionNode.get("blockNumber").asText());
        transaction.setTimeStamp(transactionNode.get("timeStamp").asText());
        transaction.setHash(transactionNode.get("hash").asText());
        transaction.setNonce(transactionNode.get("nonce").asText());
        transaction.setBlockHash(transactionNode.get("blockHash").asText());
        transaction.setFrom(transactionNode.get("from").asText());
        transaction.setTo(transactionNode.get("to").asText());
        transaction.setContractAddress(transactionNode.get("contractAddress").asText());
        transaction.setTokenId(transactionNode.get("tokenID").asText());  // Add mapping for tokenID
        transaction.setTokenName(transactionNode.get("tokenName").asText());
        transaction.setTokenSymbol(transactionNode.get("tokenSymbol").asText());
        transaction.setTokenDecimal(transactionNode.get("tokenDecimal").asText());
        transaction.setTransactionIndex(transactionNode.get("transactionIndex").asText());
        transaction.setGas(transactionNode.get("gas").asText());
        transaction.setGasPrice(transactionNode.get("gasPrice").asText());
        transaction.setGasUsed(transactionNode.get("gasUsed").asText());
        transaction.setCumulativeGasUsed(transactionNode.get("cumulativeGasUsed").asText());
        transaction.setConfirmations(transactionNode.get("confirmations").asText());

        return transaction;
    }
}