package com.bank.kata.security.service;

import com.bank.kata.domain.model.Client;
import com.bank.kata.repository.ClientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private ClientRepository clientRepository;

    public MyUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Client client = clientRepository.findClientByEmail(email);
        return new User(client.getEmail(), client.getPassword(), new ArrayList<>());
    }
}
