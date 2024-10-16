package org.example.backend.blockchain.solana.transaction.entity.transaction.message.header;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Header {

    private Long numRequiredSignatures;
    private Long numReadonlySignedAccounts;
    private Long numReadonlyUnsignedAccounts;
}
