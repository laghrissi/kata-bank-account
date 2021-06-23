package com.bank.kata.repository;


import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAllAccount() {
        // When
        List<Account> accounts = accountRepository.findAll();

        // Then
        Assertions.assertThat(accounts).hasSize(2);
    }
}
