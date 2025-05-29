package com.tradinsimulator.tradingsimulator.entities;

import java.time.Instant;

public class UserCurrency {

    private int id;

    private int userId;

    private String currency;

    private double ammount;


    private Instant createdAt;


    private Instant updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getammount() {
        return ammount;
    }

    public void setammount(double ammount) {
        this.ammount = ammount;
    }
}
