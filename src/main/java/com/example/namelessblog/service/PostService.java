package com.example.namelessblog.service;

import com.example.namelessblog.rest.dto.PostDTO;
import org.springframework.stereotype.Repository;

public interface PostService {

    void deletePost(Long id, Long userId);
    void savePost(PostDTO dto);
}
