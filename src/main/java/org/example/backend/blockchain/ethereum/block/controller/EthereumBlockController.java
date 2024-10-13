package org.example.backend.blockchain.ethereum.block.controller;

import org.example.backend.blockchain.ethereum.block.entity.EthereumBlockDto;
import org.example.backend.blockchain.ethereum.block.service.EthereumBlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ethereum/block")
public class EthereumBlockController {

    private final EthereumBlockService ethereumBlockService;

    public EthereumBlockController(EthereumBlockService ethereumBlockService) {
        this.ethereumBlockService = ethereumBlockService;
    }

    @GetMapping("/{blockNumber}")
    public ResponseEntity<EthereumBlockDto> getBlock(@PathVariable Long blockNumber) {
        Optional<EthereumBlockDto> ethereumBlockDtoOptional = ethereumBlockService.getBlockByNumber(blockNumber);

        if (ethereumBlockDtoOptional.isPresent()) {
            return ResponseEntity.ok(ethereumBlockDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/minedBlocks/{address}")
    public ResponseEntity<List<EthereumBlockDto>> getMinedBlocks(@PathVariable String address, @RequestParam(defaultValue = "blocks") String blockType) {
        Optional<List<EthereumBlockDto>> optionalEthereumBlockDtos = ethereumBlockService.getMinedBlocks(address, blockType);

        if (optionalEthereumBlockDtos.isPresent()) {
            return ResponseEntity.ok(optionalEthereumBlockDtos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ethSupply")
    public ResponseEntity<String> getEthSupply() {
        Optional<String> optionalEthSupply =  ethereumBlockService.getEthSupply();

        if (optionalEthSupply.isPresent()) {
            return ResponseEntity.ok(optionalEthSupply.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ethPrice")
    public ResponseEntity<String> getEthPrice() {
        Optional<String> optionalEthPrice =  ethereumBlockService.getEthPrice();

        if (optionalEthPrice.isPresent()) {
            return ResponseEntity.ok(optionalEthPrice.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
