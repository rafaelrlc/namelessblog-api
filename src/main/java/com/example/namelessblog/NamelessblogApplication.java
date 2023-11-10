package com.example.namelessblog;

import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NamelessblogApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired UserRepository users) {
		return args -> {
			User user1 = new User("Rafael", "rafael@gmail.com", "123123", "RafaelRibeiro");
			users.save(user1);


		};
	}

	public static void main(String[] args) {
		SpringApplication.run(NamelessblogApplication.class, args);
	}

}
