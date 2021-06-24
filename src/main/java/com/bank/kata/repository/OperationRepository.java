package com.bank.kata.repository;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    Page<Operation> findByAccount(Account account, Pageable pageable);
    List<Operation> findAllByAccountOrderByDateDesc(Account account);
}
