package com.example.demo.service;

import com.example.demo.model.BarterTransaction;

import java.util.List;

public interface TransactionService {
    BarterTransaction createTransaction(Long matchId);
    BarterTransaction getTransactionById(Long id);
    List<BarterTransaction> getAllTransactions();
    BarterTransaction completeTransaction(Long id, double rating);
    List<BarterTransaction> getTransactionsByStatus(String status);
}
