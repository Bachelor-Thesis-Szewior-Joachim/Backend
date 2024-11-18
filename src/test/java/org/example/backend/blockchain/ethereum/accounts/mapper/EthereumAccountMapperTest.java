package org.example.backend.blockchain.ethereum.accounts.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccount;
import org.example.backend.blockchain.ethereum.accounts.entity.EthereumAccountDto;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransaction;
import org.example.backend.blockchain.ethereum.transaction.entity.EthereumTransactionDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EthereumAccountMapperTest {

    @Test
    void mapAccountDtoToAccount() {
        EthereumAccountDto accountDto = EthereumAccountDto.builder()
                .balance("1000")
                .transactions(List.of(new EthereumTransactionDto()))
                .build();

        EthereumAccount account = EthereumAccountMapper.mapAccountDtoToAccount(accountDto);

        assertNotNull(account);
        assertEquals(accountDto.getBalance(), account.getBalance());
        assertEquals(accountDto.getTransactions().size(), account.getTransactions().size());
    }

    @Test
    void mapAccountToAccountDto() {
        EthereumAccount account = EthereumAccount.builder()
                .balance("1000")
                .transactions(List.of(new EthereumTransaction()))
                .build();

        EthereumAccountDto accountDto = EthereumAccountMapper.mapAccountToAccountDto(account);

        assertNotNull(accountDto);
        assertEquals(account.getBalance(), accountDto.getBalance());
        assertEquals(account.getTransactions().size(), accountDto.getTransactions().size());
    }

    @Test
    void mapToEthereumAccount() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode balanceNode = objectMapper.readTree("{\"result\": \"1000\"}");
        JsonNode transactionHistoryNode = objectMapper.readTree("{\"result\": []}");

        EthereumAccount account = EthereumAccountMapper.mapToEthereumAccount(balanceNode, transactionHistoryNode);

        assertNotNull(account);
        assertEquals("1000", account.getBalance());
        assertEquals(0, account.getTransactions().size());
    }

    @Test
    void mapToEthereumTransactionDto() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode transactionNode = objectMapper.readTree("{\"blockNumber\": \"1\", \"timeStamp\": \"1234567890\", \"hash\": \"0xhash\", \"nonce\": \"0\", \"blockHash\": \"0xblockHash\", \"from\": \"0xfrom\", \"to\": \"0xto\", \"contractAddress\": \"0xcontract\", \"value\": \"100\", \"tokenName\": \"Token\", \"tokenSymbol\": \"TKN\", \"tokenDecimal\": \"18\", \"transactionIndex\": \"0\", \"gas\": \"21000\", \"gasPrice\": \"20000000000\", \"gasUsed\": \"21000\", \"cumulativeGasUsed\": \"21000\", \"confirmations\": \"10\"}");

        EthereumTransactionDto transactionDto = EthereumAccountMapper.mapToEthereumTransactionDto(transactionNode);

        assertNotNull(transactionDto);
        assertEquals("1", transactionDto.getBlockNumber());
        assertEquals("1234567890", transactionDto.getTimeStamp());
        assertEquals("0xhash", transactionDto.getHash());
        assertEquals("0", transactionDto.getNonce());
        assertEquals("0xblockHash", transactionDto.getBlockHash());
        assertEquals("0xfrom", transactionDto.getFrom());
        assertEquals("0xto", transactionDto.getTo());
        assertEquals("0xcontract", transactionDto.getContractAddress());
        assertEquals("100", transactionDto.getValue());
        assertEquals("Token", transactionDto.getTokenName());
        assertEquals("TKN", transactionDto.getTokenSymbol());
        assertEquals("18", transactionDto.getTokenDecimal());
        assertEquals("0", transactionDto.getTransactionIndex());
        assertEquals("21000", transactionDto.getGas());
        assertEquals("20000000000", transactionDto.getGasPrice());
        assertEquals("21000", transactionDto.getGasUsed());
        assertEquals("21000", transactionDto.getCumulativeGasUsed());
        assertEquals("10", transactionDto.getConfirmations());
    }

    @Test
    void mapToEthereumTransactionDtoWithTokenIdAndNoValue() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode transactionNode = objectMapper.readTree("{\"blockNumber\": \"1\", \"timeStamp\": \"1234567890\", \"hash\": \"0xhash\", \"nonce\": \"0\", \"blockHash\": \"0xblockHash\", \"from\": \"0xfrom\", \"to\": \"0xto\", \"contractAddress\": \"0xcontract\", \"tokenID\": \"1\", \"tokenName\": \"Token\", \"tokenSymbol\": \"TKN\", \"tokenDecimal\": \"18\", \"transactionIndex\": \"0\", \"gas\": \"21000\", \"gasPrice\": \"20000000000\", \"gasUsed\": \"21000\", \"cumulativeGasUsed\": \"21000\", \"confirmations\": \"10\"}");

        EthereumTransactionDto transactionDto = EthereumAccountMapper.mapToEthereumTransactionDtoWithTokenIdAndNoValue(transactionNode);

        assertNotNull(transactionDto);
        assertEquals("1", transactionDto.getBlockNumber());
        assertEquals("1234567890", transactionDto.getTimeStamp());
        assertEquals("0xhash", transactionDto.getHash());
        assertEquals("0", transactionDto.getNonce());
        assertEquals("0xblockHash", transactionDto.getBlockHash());
        assertEquals("0xfrom", transactionDto.getFrom());
        assertEquals("0xto", transactionDto.getTo());
        assertEquals("0xcontract", transactionDto.getContractAddress());
        assertEquals("1", transactionDto.getTokenId());
        assertEquals("Token", transactionDto.getTokenName());
        assertEquals("TKN", transactionDto.getTokenSymbol());
        assertEquals("18", transactionDto.getTokenDecimal());
        assertEquals("0", transactionDto.getTransactionIndex());
        assertEquals("21000", transactionDto.getGas());
        assertEquals("20000000000", transactionDto.getGasPrice());
        assertEquals("21000", transactionDto.getGasUsed());
        assertEquals("21000", transactionDto.getCumulativeGasUsed());
        assertEquals("10", transactionDto.getConfirmations());
    }
}