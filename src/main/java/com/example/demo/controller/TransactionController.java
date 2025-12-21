package com.example.demo.controller;

import com.example.demo.model.BarterTransaction;
import com.example.demo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TraznsactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<BarterTransaction> createTransaction(@RequestParam Long matchId) {
        return ResponseEntity.ok(transactionService.createTransaction(matchId));
    }

    @GetMapping
    public ResponseEntity<List<BarterTransaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarterTransaction> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<BarterTransaction> completeTransaction(
            @PathVariable Long id,
            @RequestParam double rating) {
        return ResponseEntity.ok(transactionService.completeTransaction(id, rating));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<BarterTransaction>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(transactionService.getTransactionsByStatus(status));
    }
}
