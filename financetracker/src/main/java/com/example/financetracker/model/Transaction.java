// src/main/java/com/example/financetracker/model/Transaction.java

package com.example.financetracker.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A simple JPA entity to represent a financial transaction.
 * @Data from Lombok automatically generates getters, setters, toString(), etc.
 */
@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private String type; // e.g., "income" or "expense"
}
