package org.example.backend.blockchain.solana.transaction.mapper;

import org.example.backend.blockchain.solana.transaction.entity.transaction.SolanaTransaction;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.SolanaTransactionMessage;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.header.Header;
import org.example.backend.blockchain.solana.transaction.entity.transaction.message.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonSolanaTransactionMapperTest {

    @Test
    public void testMapJsonToSolanaTransaction() throws Exception {
        String jsonResponse = "{ \"result\": { \"blockTime\": \"1234567890\", \"slot\": \"12345\", \"transaction\": { \"signatures\": [\"signature1\", \"signature2\"], \"message\": { \"accountKeys\": [\"key1\", \"key2\"], \"header\": { \"numReadonlySignedAccounts\": 1, \"numReadonlyUnsignedAccounts\": 2, \"numRequiredSignatures\": 3 }, \"recentBlockhash\": \"blockhash\", \"instructions\": [ { \"programIdIndex\": \"1\", \"accounts\": [\"account1\", \"account2\"], \"data\": \"data\", \"stackHeight\": 1 } ], \"blockHash\": \"blockhash\" } } } }";

        SolanaTransaction transaction = JsonSolanaTransactionMapper.mapJsonToSolanaTransaction(jsonResponse);

        assertNotNull(transaction);
        assertEquals("1234567890", transaction.getBlockTime());
        assertEquals("12345", transaction.getSlot());
        assertEquals(List.of("signature1", "signature2"), transaction.getSignatures());

        SolanaTransactionMessage message = transaction.getMessage();
        assertNotNull(message);
        assertEquals(List.of("key1", "key2"), message.getAccountKeys());
        assertEquals("blockhash", message.getRecentBlockHash());
        assertEquals("blockhash", message.getBlockHash());

        Header header = message.getHeader();
        assertNotNull(header);
        assertEquals(1, header.getNumReadonlySignedAccounts());
        assertEquals(2, header.getNumReadonlyUnsignedAccounts());
        assertEquals(3, header.getNumRequiredSignatures());

        List<Instruction> instructions = message.getInstructions();
        assertNotNull(instructions);
        assertEquals(1, instructions.size());

        Instruction instruction = instructions.get(0);
        assertEquals("1", instruction.getProgramIdIndex());
        assertEquals(List.of("account1", "account2"), instruction.getAccounts());
        assertEquals("data", instruction.getData());
        assertEquals(1, instruction.getStackHeight());
    }
}