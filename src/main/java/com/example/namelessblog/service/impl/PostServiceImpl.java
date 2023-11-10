package com.example.namelessblog.service.impl;

import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;



}
