package com.bank.kata.service;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.rest.OperationRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {
    Operation registerOperation(OperationRequest operationRequest, Account account);

    List<Operation> getOperationByAccount(Account account, Pageable pageable);

    StringBuilder printStatements(Account account);
}
