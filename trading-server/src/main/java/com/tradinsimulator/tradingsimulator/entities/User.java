package com.tradinsimulator.tradingsimulator.entities;

import java.time.Instant;

public class User {

    private int id;


    private String username;

    private String email;

    private String passHash;

    private Double balance;

    private Instant createdAt;

    private Instant updatedAt;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassHash() {
        return passHash;
    }

    public Double getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }
}

