package org.example.backend.blockchain.ethereum.contract.controller;


import org.example.backend.blockchain.ethereum.contract.entity.contract.ContractDto;
import org.example.backend.blockchain.ethereum.contract.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ethereum/contract")
public class ContractController {

    private final ContractService contractService;
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/creationInfo")
    public ResponseEntity<List<ContractDto>> getContractCreationInfo(@RequestParam List<String> contractAddresses) {
        List<ContractDto> result = contractService.getContractsCreationInfo(contractAddresses);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get the contract creation information for a list of contract addresses
    @GetMapping("/sourcecode/{contractAddress}")
    public ResponseEntity<String> getSourceCode(@PathVariable String contractAddress) {
        // Call the service and retrieve the Optional result
        Optional<String> sourceCode = contractService.getSourceCode(contractAddress);

        // If the source code is present, return it with an HTTP 200 OK status
        if (sourceCode.isPresent()) {
            return new ResponseEntity<>(sourceCode.get(), HttpStatus.OK);
        } else {
            // If no source code was found, return HTTP 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
