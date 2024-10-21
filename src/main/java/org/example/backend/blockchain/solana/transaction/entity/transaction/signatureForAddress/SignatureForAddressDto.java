package org.example.backend.blockchain.solana.transaction.entity.transaction.signatureForAddress;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignatureForAddressDto {
     private String blockTime;
     private String confirmationStatus;
     private String err;
     private String memo;
     private String signature;
    private String slot;
}
