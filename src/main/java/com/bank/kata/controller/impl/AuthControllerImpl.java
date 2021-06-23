package com.bank.kata.controller.impl;

import com.bank.kata.controller.AuthController;
import com.bank.kata.domain.rest.AuthenticationRequest;
import com.bank.kata.domain.rest.AuthenticationResponse;
import com.bank.kata.security.service.JwtUtil;
import com.bank.kata.security.service.MyUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private AuthenticationManager authenticationManager;

    private MyUserDetailsService myUserDetailsService;

    private JwtUtil jwtUtil;

    public AuthControllerImpl(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authentication(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new com.bank.kata.exception.BadCredentialsException("bad credentials");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
