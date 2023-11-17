package com.example.namelessblog.rest.controller;


import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.rest.dto.HomePagePostDTO;
import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.rest.dto.UpdatePostDTO;
import com.example.namelessblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<Post> newPost(@RequestBody PostDTO dto) {
        Post post = postService.savePost(dto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Post> updatePost(@RequestBody UpdatePostDTO dto) {
        Post post = postService.updatePost(dto);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping()
    public void deletePost(@RequestParam Long id, @RequestParam Long userId) {
        postService.deletePost(id, userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<HomePagePostDTO>> getAllPosts() {
        List<HomePagePostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        return new ResponseEntity<>(postService.getUserPosts(userId), HttpStatus.OK);
    }

}
