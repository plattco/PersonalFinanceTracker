package com.example.financetracker.controller;

import com.example.financetracker.model.Transaction;
import com.example.financetracker.repository.CheckingRepository;
import com.example.financetracker.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000") 
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final CheckingRepository checkingRepository;

    public TransactionController(TransactionRepository transactionRepository, CheckingRepository checkingRepository) {
        this.transactionRepository = transactionRepository;
        this.checkingRepository = checkingRepository;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) throws URISyntaxException {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.created(new URI("/api/transactions/" + savedTransaction.getId()))
                .body(savedTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionRepository.findById(id)
                .map(existingTransaction -> {
                    existingTransaction.setDescription(transaction.getDescription());
                    existingTransaction.setAmount(transaction.getAmount());
                    existingTransaction.setDate(transaction.getDate());
                    existingTransaction.setType(transaction.getType());
                    return ResponseEntity.ok(transactionRepository.save(existingTransaction));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<BigDecimal> caulculateCheckingAmount(){
        return ResponseEntity.ok(checkingRepository.getAmount());
    }
}