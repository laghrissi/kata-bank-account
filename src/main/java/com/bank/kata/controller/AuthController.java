package com.bank.kata.controller;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.rest.AuthenticationRequest;
import com.bank.kata.domain.rest.AuthenticationResponse;
import com.bank.kata.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface AuthController {

    @io.swagger.v3.oas.annotations.Operation(summary = "Authentication by email and password of client",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @RequestMapping(method = RequestMethod.POST, value = "/api/authenticate")
    ResponseEntity<AuthenticationResponse> authentication(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception;
}
