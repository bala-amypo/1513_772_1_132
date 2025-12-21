package com.example.demo.repository;

import com.example.demo.model.BarterTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<BarterTransaction, Long> {
    List<BarterTransaction> findByStatus(String status);
}
