package com.example.namelessblog.service.impl;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.UserRepository;
import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.service.PostService;
import com.example.namelessblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public void savePost(PostDTO dto) {
        User user = userService.findUserById(dto.userId());
        Date date = new Date();
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(user);
        post.setDate(date);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id, Long userId) {
        // futuramente o user vai ser pego pelo token e não pelo id passado na requisição
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        User user = userService.findUserById(userId);

        // verify if the user is the author of the post
        if (!Objects.equals(post.getAuthor().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to delete post");
        }

        postRepository.deleteById(id);
    }

}
