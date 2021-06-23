package com.bank.kata.domain.rest;

import com.bank.kata.domain.util.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class OperationRequest {

    @NotNull
    @Min(0)
    private Double operationAmount;

    @NotNull
    private OperationType operationType;
}
