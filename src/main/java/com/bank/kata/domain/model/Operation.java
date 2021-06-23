package com.bank.kata.domain.model;

import com.bank.kata.domain.util.AccountType;
import com.bank.kata.domain.util.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public  class Operation {
    @Id
    @GeneratedValue(generator = "UUI")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "opeartion_id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID operationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @Column
    private Double amount;

    @Column(name = "operation_date")
    private LocalDateTime date;

    @Column
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;




}