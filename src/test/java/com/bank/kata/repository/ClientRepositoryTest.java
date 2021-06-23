package com.bank.kata.repository;


import com.bank.kata.domain.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    void findAllClient() {
        // When
        List<Client> clients = clientRepository.findAll();

        // Then
        Assertions.assertThat(clients).hasSize(2);
    }

    @Test
    void findClientByEmail() {
        // When
        Client clientByEmail = clientRepository.findClientByEmail("laghrissi@gmail.com");

        // Then
        Assertions.assertThat(clientByEmail.getEmail()).isEqualTo("laghrissi@gmail.com");
    }
}
