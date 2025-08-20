package com.example.financetracker.repository;
import com.example.financetracker.model.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CheckingRepository {
    private final TransactionRepository transactionRepository;

    public CheckingRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public BigDecimal getAmount() {
        List<Transaction> transactions = transactionRepository.findAll();
        BigDecimal amount = BigDecimal.valueOf(0.000000);
        for(Transaction transaction : transactions){
            amount = amount.add(transaction.getAmount());
        }
        return amount;
    }
}
