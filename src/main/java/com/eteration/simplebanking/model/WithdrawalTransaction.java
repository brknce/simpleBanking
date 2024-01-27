package com.eteration.simplebanking.model;


public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction() {
        super();
    }

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void calculateBalance(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WithdrawalTransaction;
    }


}


