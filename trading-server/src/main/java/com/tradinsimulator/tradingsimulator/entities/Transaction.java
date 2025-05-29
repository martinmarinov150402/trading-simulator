package com.tradinsimulator.tradingsimulator.entities;

import java.time.Instant;



public class Transaction {



    private int id;

    private int userId;

    private String currency;

    private double ammount;

    private double money;

    public int getUserId() {
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

    private Instant createdAt;

    public void setUserId(int userId) {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
