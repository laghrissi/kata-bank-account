package com.bank.kata.domain.util;

import org.springframework.core.convert.converter.Converter;

public class StringToOperationTypeConverter implements Converter<String, OperationType> {

    @Override
    public OperationType convert(String s) {
        try {
            return OperationType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
