package com.bank.kata.controller;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.rest.OperationRequest;
import com.bank.kata.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/account/")
public interface AccountController {

    @io.swagger.v3.oas.annotations.Operation(summary = "Get Account By Id",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    ResponseEntity<Account> getAccountById(@PathVariable("id") String id, Authentication authentication);


    @io.swagger.v3.oas.annotations.Operation(summary = "Register operation (DEPOSIT or WITHDRAWAL)",
            responses = {
                    @ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @RequestMapping(method = RequestMethod.POST, value = "{id}/operation")
    void registerOperation(@PathVariable("id") String id, @RequestBody @Valid OperationRequest operationRequest, Authentication authentication);


    @io.swagger.v3.oas.annotations.Operation(summary = "Get list of operations by account",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Operation.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Account not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @RequestMapping(method = RequestMethod.GET, value = "{id}/operations")
    ResponseEntity<List<Operation>> getOperationByAccount(@PathVariable("id") String id, Pageable pageable, Authentication authentication);
}
