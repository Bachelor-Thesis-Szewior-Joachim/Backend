package org.example.backend.blockchain.data.ethereum;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Service
public class EthereumService {

    private final Web3j web3j;

    public EthereumService() {
        this.web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/8c409480bf6646acb9bbb1dc826ff097"));
    }

    public String getClientVersion() throws ExecutionException, InterruptedException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
        return web3ClientVersion.getWeb3ClientVersion();
    }

    public BigDecimal getAccountBalance(String address) throws ExecutionException, InterruptedException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, org.web3j.protocol.core.DefaultBlockParameterName.LATEST)
                .sendAsync().get();
        BigInteger wei = ethGetBalance.getBalance();
        return Convert.fromWei(wei.toString(), Convert.Unit.ETHER);
    }

    public EthTransaction getTransactionByHash(String transactionHash) throws ExecutionException, InterruptedException {
        return web3j.ethGetTransactionByHash(transactionHash).sendAsync().get();
    }

    public EthBlock getBlockByNumber(BigInteger blockNumber) throws ExecutionException, InterruptedException {
        return web3j.ethGetBlockByNumber(org.web3j.protocol.core.DefaultBlockParameter.valueOf(blockNumber), true)
                .sendAsync().get();
    }

}
