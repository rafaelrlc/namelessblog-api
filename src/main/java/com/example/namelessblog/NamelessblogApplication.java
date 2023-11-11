package com.example.namelessblog;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class NamelessblogApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired UserRepository users , @Autowired PostRepository posts) {
		return args -> {
			User user1 = new User("Rafael", "rafael@gmail.com", "123123", "RafaelRibeiro");
			users.save(user1);

			Post post1 = new Post("Primeiro post", "Conteúdo do primeiro post", user1, new Date());
			posts.save(post1);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(NamelessblogApplication.class, args);
	}

}
