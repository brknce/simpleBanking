package com.eteration.simplebanking.model;


public class DepositTransaction extends Transaction {
    public DepositTransaction() {
        super();
    }

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void calculateBalance(Account account) {
        account.deposit(this.getAmount());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DepositTransaction;
    }


}
