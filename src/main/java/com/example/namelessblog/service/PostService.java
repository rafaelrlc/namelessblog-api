package com.example.namelessblog.service;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.rest.dto.HomePagePostDTO;
import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.rest.dto.UpdatePostDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostService {

    void deletePost(Long id, Long userId);

    Post savePost(PostDTO dto);

    Post updatePost(UpdatePostDTO dto);

    Post getPost(Long id);

    List<HomePagePostDTO> getAllPosts();

    List<Post> getUserPosts(Long userId);
}
