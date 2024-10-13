package org.example.backend.blockchain.ethereum.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EthereumTransactionDto {
    private String blockNumber;
    private String blockHash;
    private String timeStamp;
    private String hash;
    private String nonce;
    private String transactionIndex;
    private String from;
    private String to;
    private String value;
    private String gas;
    private String gasPrice;
    private String input;
    private String methodId;
    private String functionName;
    private String contractAddress;
    private String cumulativeGasUsed;
    private String txReceiptStatus;
    private String gasUsed;
    private String confirmations;
    private String isError;
    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;
    private String tokenId;
    private String traceId;
    private String type;
    private String errorCode;

    public EthereumTransactionDto() {

    }
}
