package com.example.namelessblog.service.impl;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.UserRepository;
import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void savePost(PostDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Date date = new Date();
        Post post = new Post(dto.getTitle(), dto.getContent(), user, date);
        postRepository.save(post);
    }

    public void deletePostById(Long id, Long userId) {
        // futuramente o user vai ser pego pelo token e não pelo id passado na requisição
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!Objects.equals(post.getAuthor().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to delete post");
        }

        postRepository.deleteById(id);
    }

}
