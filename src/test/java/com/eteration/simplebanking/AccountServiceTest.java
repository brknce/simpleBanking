package com.eteration.simplebanking;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void it_should_find_account() {
        Account account = new Account("Kerem Karaca", "669-7788");
        when(accountRepository.findByAccountNumber("669-7788")).thenReturn(account);

        var founded = accountService.findAccount("669-7788");
        assertThat(founded).isNotNull();
        assertThat(founded).isEqualTo(account);
    }
}
