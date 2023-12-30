package com.example.namelessblog;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.Tags;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.TagsRepository;
import com.example.namelessblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class NamelessblogApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired UserRepository users, @Autowired PostRepository posts, @Autowired TagsRepository tags) {
        return args -> {
            User user1 = new User("Rafael", "rafael@gmail.com", "123123", "RafaelRibeiro");
            users.save(user1);

            Post post1 = new Post();
            post1.setTitle("First-Post");
            post1.setContent("Conteúdo do primeiro post");
            post1.setAuthor(user1);
            post1.setDate(new Date());
            posts.save(post1);

            Post post2 = new Post();
            post2.setTitle("Second-Post");
            post2.setContent("Conteúdo do 2 post");
            post2.setDate(new Date());
            post2.setAuthor(user1);
            posts.save(post2);


            Tags badge1 = new Tags("Art", "yellow");
            Tags badge3 = new Tags("Anime", "blue");
            Tags badge4 = new Tags("Games", "red");
            Tags badge5 = new Tags("Music", "white");
            Tags badge6 = new Tags("Movies", "indigo");
            Tags badge7 = new Tags("Other", "gray");
            Tags badge8 = new Tags("AI", "cyan");

            tags.save(badge1);
            tags.save(badge3);
            tags.save(badge4);
            tags.save(badge5);
            tags.save(badge6);
            tags.save(badge7);
            tags.save(badge8);


            post2.getTags().add(badge1);
            post2.getTags().add(badge3);

            posts.save(post2);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(NamelessblogApplication.class, args);
    }

}
