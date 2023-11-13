package com.example.namelessblog.rest.controller;


import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public void newPost(@RequestBody PostDTO dto) {
        postService.savePost(dto);
    }

    @DeleteMapping
    public void deletePost(@RequestParam Long id, @RequestParam Long userId) {  //temporarily using request param
        postService.deletePost(id, userId);
    }

}
