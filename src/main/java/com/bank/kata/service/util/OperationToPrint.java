package com.bank.kata.service.util;

import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.util.OperationType;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OperationToPrint {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static String HEADER =  "Date               ||   amount    ||   Balance   \n";

    public static StringBuilder operationToString(List<Operation> operations) {
        StringBuilder operationString = new StringBuilder();
        operationString.append(HEADER);
        operations.stream().forEach(operation -> {
            operationString.append(operation.getDate().format(FORMATTER))
                    .append("   ||   ")
                    .append(OperationType.DEPOSIT.equals(operation.getType()) ? operation.getAmount() : "-" + operation.getAmount())
                    .append("   ||   ")
                    .append(operation.getBalance() + "\n");
        });
        return operationString;
    }
}
