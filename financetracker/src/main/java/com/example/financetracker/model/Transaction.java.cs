namespace DefaultNamespace;

public class Transaction_java
{
    package com.example.financetracker.model;

    import lombok.Data;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import java.math.BigDecimal;
    import java.time.LocalDate;

    @Entity
        @Data
    public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String description;
        private BigDecimal amount;
        private LocalDate date;
        private String type;
    }
}