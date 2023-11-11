package com.example.namelessblog.service;

import com.example.namelessblog.rest.dto.PostDTO;
import org.springframework.stereotype.Repository;

public interface PostService {

    void deletePostById(Long id, Long userId);
    void savePost(PostDTO dto);
}
