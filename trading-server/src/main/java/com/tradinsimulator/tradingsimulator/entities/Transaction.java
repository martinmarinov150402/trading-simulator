package com.tradinsimulator.tradingsimulator.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue()
    private int id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String currency;


    @Column(nullable = false)
    private double ammount;

    @Column(nullable = false)
    private double money;

    public UUID getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmmount() {
        return ammount;
    }

    public double getMoney() {
        return money;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
