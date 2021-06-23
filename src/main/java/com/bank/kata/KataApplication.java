package com.bank.kata;

import com.bank.kata.domain.model.Account;
import com.bank.kata.domain.model.Client;
import com.bank.kata.domain.model.Operation;
import com.bank.kata.domain.util.AccountType;
import com.bank.kata.domain.util.OperationType;
import com.bank.kata.repository.AccountRepository;
import com.bank.kata.repository.ClientRepository;
import com.bank.kata.repository.OperationRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;


@OpenAPIDefinition(
		info = @Info(
				title = "Bank Kata",
				version = "0.0",
				description = "Bank API",
				contact = @Contact(url = "https://www.linkedin.com/in/laghrissiothmane/", name = "Othmane Laghrissi", email = "othmane.laghrissi@vo2-group.com")
		)
)
@SpringBootApplication
public class KataApplication {

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AccountRepository accountRepository, ClientRepository clientRepository, OperationRepository operationRepository) {
		return args -> {
			final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			final String encodedPassword = passwordEncoder.encode("pwd");

			Client client = Client.builder()
					.firstName("othmane")
					.lastName("laghrissi")
					.email("laghrissi@gmail.com")
					.password(encodedPassword)
					.build();
			client = clientRepository.save(client);

			Client client_2 = Client.builder()
					.firstName("tom")
					.lastName("ford")
					.email("tome.ford@gmail.com")
					.password(passwordEncoder.encode("tompwd"))
					.build();
			clientRepository.save(client_2);

			Account account_1 = Account.builder()
					.accountNumber("A258J753N")
					.client(client)
					.type(AccountType.CLASSIC)
					.balance(5000.0)
					.build();
			account_1 = accountRepository.save(account_1);

			Account account_2 = Account.builder()
					.accountNumber("JN95663B")
					.client(client)
					.balance(0.0)
					.type(AccountType.SAVING)
					.build();
			account_2 = accountRepository.save(account_2);


			Operation operation_1 = Operation.builder()
					.amount(6200.0)
					.balance(6200.0)
					.account(account_1)
					.date(LocalDateTime.now().minusHours(5))
					.type(OperationType.DEPOSIT)
					.build();
			operation_1 = operationRepository.save(operation_1);

			Operation operation_2 = Operation.builder()
					.amount(1200.0)
					.balance(5000.0)
					.account(account_1)
					.date(LocalDateTime.now().minusHours(2))
					.type(OperationType.WITHDRAWAL)
					.build();
			operation_2 = operationRepository.save(operation_2);
		};
	}


}
