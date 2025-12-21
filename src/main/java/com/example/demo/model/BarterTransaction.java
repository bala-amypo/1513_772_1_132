package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class BarterTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;
    private String status;
    private double rating;

    public BarterTransaction() {}

    public BarterTransaction(Long matchId) {
        this.matchId = matchId;
        this.status = "PENDING";
    }

    public BarterTransaction(Long matchId, String status, double rating) {
        this.matchId = matchId;
        this.status = status;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public String getStatus() {
        return status;
    }

    public double getRating() {
        return rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
