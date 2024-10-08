package org.example.backend.blockchain.ethereum.block.controller;

import org.example.backend.blockchain.ethereum.block.service.EthereumBlockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ethereum/block")
public class EthereumBlockController {

    private final EthereumBlockService ethereumBlockService;

    public EthereumBlockController(EthereumBlockService ethereumBlockService) {
        this.ethereumBlockService = ethereumBlockService;
    }

    @GetMapping("/{blockNumber}")
    public String getBlock(@PathVariable Long blockNumber) {
        return ethereumBlockService.getBlockByNumber(blockNumber);
    }

    @GetMapping("/minedBlocks/{address}")
    public String getMinedBlocks(@PathVariable String address, @RequestParam(defaultValue = "blocks") String blockType) {
        return ethereumBlockService.getMinedBlocks(address, blockType);
    }

    @GetMapping("/ethSupply")
    public String getEthSupply() {
        return ethereumBlockService.getEthSupply();
    }

    @GetMapping("/ethPrice")
    public String getEthPrice() {
        return ethereumBlockService.getEthPrice();
    }
}
