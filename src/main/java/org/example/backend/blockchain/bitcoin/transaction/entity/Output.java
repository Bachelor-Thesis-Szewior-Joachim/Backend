package org.example.backend.blockchain.bitcoin.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Output {
    private long value;
    private String script;
    private List<String> addresses;
}
