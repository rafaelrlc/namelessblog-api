package com.example.namelessblog.service;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.rest.dto.PostDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostService {

    void deletePost(Long id, Long userId);
    void savePost(PostDTO dto);
    List<Post> getPosts();
}
