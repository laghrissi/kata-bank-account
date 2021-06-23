package com.bank.kata.service.impl;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.rest.OperationRequest;
import com.bank.kata.domain.util.OperationType;
import com.bank.kata.repository.OperationRepository;
import com.bank.kata.service.AccountService;
import com.bank.kata.service.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OperationServiceImpl implements OperationService {
    private OperationRepository operationRepository;

    private AccountService accountService;

    public OperationServiceImpl(OperationRepository operationRepository, AccountService accountService) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
    }

    @Override
    public Operation registerOperation(OperationRequest operationRequest, Account account) {
        final Operation operation = Operation.builder()
                .account(account)
                .amount(operationRequest.getOperationAmount())
                .date(LocalDateTime.now())
                .type(operationRequest.getOperationType())
                .build();
        manageBalanceAccount(operationRequest, account);
        operation.setBalance(account.getBalance());
        accountService.save(account);
        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getOperationByAccount(Account account, Pageable pageable) {
        final Page<Operation> operationsByAccount = operationRepository.findByAccount(account, pageable);
        return operationsByAccount.getContent();
    }

    private void manageBalanceAccount(OperationRequest operationRequest, Account account) {
        if (OperationType.DEPOSIT.equals(operationRequest.getOperationType())) {
            account.setBalance(account.getBalance() + operationRequest.getOperationAmount());
        } else {
            account.setBalance(account.getBalance() - operationRequest.getOperationAmount());
        }
    }
}
