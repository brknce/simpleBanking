package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public void updateAccount(Account account) throws InsufficientBalanceException {
        accountRepository.save(account);
    }

}
