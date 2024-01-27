package com.eteration.simplebanking;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    void it_should_find_account_by_accountNumber() {
        Account account = new Account("Kerem Karaca", "669-7788");
        accountRepository.save(account);
        var founded = accountRepository.findByAccountNumber("669-7788");
        assertThat(founded).isNotNull();
        assertThat(founded.getAccountNumber()).isEqualTo(account.getAccountNumber());
    }

}