package com.example.namelessblog;

import com.example.namelessblog.domain.entity.Badge;
import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.BadgeRepository;
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
    public CommandLineRunner commandLineRunner(@Autowired UserRepository users, @Autowired PostRepository posts, @Autowired BadgeRepository badges) {
        return args -> {
            User user1 = new User("Rafael", "rafael@gmail.com", "123123", "RafaelRibeiro");
            users.save(user1);

            Post post1 = new Post();
            post1.setTitle("Primeiro post");
            post1.setContent("Conteúdo do primeiro post");
            post1.setAuthor(user1);
            post1.setDate(new Date());
            posts.save(post1);

            Post post2 = new Post();
            post2.setTitle("2 post");
            post2.setContent("Conteúdo do 2 post");
            post2.setDate(new Date());
            post2.setAuthor(user1);
            posts.save(post2);


            Badge badge1 = new Badge("Art", "yellow");
            Badge badge2 = new Badge("Software", "blue");

            badges.save(badge1);
            badges.save(badge2);

            post2.getBadges().add(badge1);
            post2.getBadges().add(badge2);

            posts.save(post2);






        };
    }

    public static void main(String[] args) {
        SpringApplication.run(NamelessblogApplication.class, args);
    }

}
