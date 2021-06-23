package com.bank.kata.service;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.util.AccountType;
import com.bank.kata.repository.AccountRepository;
import com.bank.kata.service.impl.AccountServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService = new AccountServiceImp(accountRepository);


    @Test
    void getAccountById() {
        final UUID accountId = UUID.randomUUID();
        final Account account = Account.builder()
                .accountId(accountId)
                .accountNumber("AN123456")
                .balance(1500.0)
                .type(AccountType.CLASSIC)
                .build();

        Mockito.doReturn(Optional.of(account)).when(accountRepository).findById(accountId);

        final Account result = accountService.getAccountById(accountId).get();
        Assertions.assertThat(result.getAccountId()).isEqualTo(accountId);
        Assertions.assertThat(result.getAccountNumber()).isEqualTo("AN123456");


    }
}
