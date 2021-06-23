package com.bank.kata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class RestApi {

    @GetMapping
    public String getTest() {
        return "test";
    }
}
