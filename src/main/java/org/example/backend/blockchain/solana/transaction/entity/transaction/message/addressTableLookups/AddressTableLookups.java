package org.example.backend.blockchain.solana.transaction.entity.transaction.message.addressTableLookups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressTableLookups {
    private String accountKey;
    private List<String> writableIndexes;
    private List<String> readonlyIndexes;
}
