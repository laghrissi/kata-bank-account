package com.bank.kata.controller.impl;

import com.bank.kata.controller.AccountController;
import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.rest.OperationRequest;
import com.bank.kata.exception.AccessDeniedException;
import com.bank.kata.exception.AccountNotFoundException;
import com.bank.kata.service.AccountService;
import com.bank.kata.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountControllerImpl implements AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountControllerImpl.class);

    private AccountService accountService;

    private OperationService operationService;

    public AccountControllerImpl(AccountService accountService, OperationService operationService) {
        this.accountService = accountService;
        this.operationService = operationService;
    }

    @Override
    public ResponseEntity<Account> getAccountById(String id, Authentication authentication) {
        logger.info("getAccountById(): account id {}", id);
        final Optional<Account> account = accountService.getAccountById(UUID.fromString(id));
        verify(id, authentication, account);
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void registerOperation(String id, OperationRequest operationRequest, Authentication authentication) {
        logger.info("registerOperation(): account id {}, operation amount {}", id, operationRequest.getOperationAmount());
        final Optional<Account> account = accountService.getAccountById(UUID.fromString(id));
        verify(id, authentication, account);
        operationService.registerOperation(operationRequest, account.get());
    }

    @Override
    public ResponseEntity<List<Operation>> getOperationByAccount(String id, Pageable pageable, Authentication authentication) {
        logger.info("getOperationByAccount(): account id {}", id);
        final Optional<Account> account = accountService.getAccountById(UUID.fromString(id));
        verify(id, authentication, account);
        final List<Operation> operationByAccount = operationService.getOperationByAccount(account.get(),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("date")));
        return new ResponseEntity<>(operationByAccount, HttpStatus.OK);
    }

    private void verify(String id, Authentication authentication, Optional<Account> account) {
        if (!account.isPresent()) {
            throw new AccountNotFoundException(String.format("Not Found account with id %s", id));
        }

        if (authentication == null || !account.get().getClient().getEmail().equals(authentication.getName())) {
            throw new AccessDeniedException("Access Denied");
        }
    }

}
