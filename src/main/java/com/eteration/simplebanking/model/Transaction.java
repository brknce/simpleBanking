package com.eteration.simplebanking.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction implements Serializable {
    public LocalDateTime date;
    public String approvalCode;
    public double amount;

    public Transaction() {
        this.date = LocalDateTime.now();
        this.approvalCode = UUID.randomUUID().toString();
    }

    public Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public abstract void calculateBalance(Account account) throws InsufficientBalanceException;

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public abstract TransactionType getType();

}
