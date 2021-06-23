package com.bank.kata.repository;


import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OperationRepositoryTest {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAllOperation() {
        // When
        List<Operation> operations = operationRepository.findAll();

        // Then
        Assertions.assertThat(operations).hasSize(2);
    }

    @Test
    void findOperationByAccount() {
        // When
        final Account account = accountRepository.findByAccountNumber("A258J753N");
        final Page<Operation> pageOperation = operationRepository.findByAccount(account, PageRequest.of(0, 2, Sort.by("date")));
        final List<Operation> operations = pageOperation.getContent();

        // Then
        Assertions.assertThat(operations).hasSize(2);
    }
}
