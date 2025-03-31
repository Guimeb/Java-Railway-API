package me.dio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import me.dio.api.domain.model.Transaction;
import me.dio.api.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        var transaction = transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transactionToCreate) {
        var transactionCreated = transactionService.create(transactionToCreate);
        return ResponseEntity.ok(transactionCreated);
    }
}