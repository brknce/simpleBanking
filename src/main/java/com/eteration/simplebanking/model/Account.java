package com.eteration.simplebanking.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "owner")
    private String owner;

    @Column(name = "balance")
    private double balance;

    @ElementCollection(targetClass = Transaction.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "transactions", joinColumns = @JoinColumn(name = "account_number"))
    @Column(name = "transactions", nullable = false, length = 1000)
    private List<Transaction> transactions;

    public Account() {

    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        transactions = new ArrayList<>();
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transactions.add(transaction);
        transaction.calculateBalance(this);
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdraw");
        }
        this.balance -= amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public String getOwner() {
        return owner;
    }


    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
