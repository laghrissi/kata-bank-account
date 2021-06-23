package com.bank.kata.service.impl;

import com.bank.kata.domain.model.Account;
import com.bank.kata.exception.AccountNotFoundException;
import com.bank.kata.repository.AccountRepository;
import com.bank.kata.service.AccountService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccountById(UUID accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
