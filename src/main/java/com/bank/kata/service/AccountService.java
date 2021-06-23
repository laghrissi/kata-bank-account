package com.bank.kata.service;

import com.bank.kata.domain.model.Account;

import java.util.Optional;
import java.util.UUID;


public interface AccountService {
    Optional<Account> getAccountById(UUID accountId);

    Account save(Account account);
}
