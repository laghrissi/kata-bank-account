package com.bank.kata.service;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.rest.OperationRequest;
import com.bank.kata.domain.util.AccountType;
import com.bank.kata.domain.util.OperationType;
import com.bank.kata.repository.OperationRepository;
import com.bank.kata.service.impl.OperationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {
    @Mock
    AccountService accountService;

    @Mock
    OperationRepository operationRepository;

    @InjectMocks
    OperationService operationService = new OperationServiceImpl(operationRepository, accountService);


    @Test
    void registerOperation() {
        final UUID accountId = UUID.randomUUID();
        final Account account = Account.builder()
                .accountId(accountId)
                .accountNumber("AN123456")
                .balance(1500.0)
                .type(AccountType.CLASSIC)
                .build();

        final Operation operation = Operation.builder()
                .account(account)
                .amount(500.0)
                .date(LocalDateTime.now())
                .type(OperationType.DEPOSIT)
                .build();

        final OperationRequest operationRequest = new OperationRequest(500.0, OperationType.DEPOSIT);

        Mockito.doReturn(operation).when(operationRepository).save(Mockito.any());

        final Operation result = operationService.registerOperation(operationRequest, account);

        Assertions.assertThat(result.getAmount()).isEqualTo(operation.getAmount());
    }

}
