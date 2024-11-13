package org.example.backend.cryptocurrency.globalMarket.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FearAndGreedDto {

    private int value;
    private String valueClassification;
    private String date;
}
