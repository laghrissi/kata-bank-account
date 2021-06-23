package com.bank.kata.domain.model;

import com.bank.kata.domain.util.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "UUI")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "account_id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID accountId;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
