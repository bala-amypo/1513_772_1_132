package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BarterTransaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;

    public TransactionServiceImpl(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public BarterTransaction createTransaction(Long matchId) {
        return repo.save(new BarterTransaction(matchId, "INITIATED", 0));
    }

    @Override
    public BarterTransaction getTransactionById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    @Override
    public List<BarterTransaction> getAllTransactions() {
        return repo.findAll();
    }

    @Override
    public BarterTransaction completeTransaction(Long id, double rating) {
        BarterTransaction tx = getTransactionById(id);
        tx.setStatus("COMPLETED");
        tx.setRating(rating);
        return repo.save(tx);
    }

    @Override
    public List<BarterTransaction> getTransactionsByStatus(String status) {
        return repo.findByStatus(status);
    }
}
