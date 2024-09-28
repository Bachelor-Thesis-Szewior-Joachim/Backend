package org.example.backend.blockchain.transaction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    @Id
    private Long id;
}
