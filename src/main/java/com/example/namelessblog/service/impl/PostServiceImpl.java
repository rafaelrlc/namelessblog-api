package com.example.namelessblog.service.impl;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.Tags;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.TagsRepository;
import com.example.namelessblog.repository.UserRepository;
import com.example.namelessblog.rest.dto.HomePagePostDTO;
import com.example.namelessblog.rest.dto.PostDTO;
import com.example.namelessblog.rest.dto.UpdatePostDTO;
import com.example.namelessblog.service.PostService;
import com.example.namelessblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagsRepository tagsRepository;
    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService, TagsRepository tagsRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.tagsRepository = tagsRepository;
    }

    @Override
    public Post savePost(PostDTO dto) {
        User user = userService.findUserById(dto.userId());

        Set<Long> badgesId = Optional.ofNullable(dto.tagsId()).orElse(Set.of()); // if badgesId is null, set it to an empty set

        List<Tags> tags = new ArrayList<>();
        for (Long id : badgesId) {
            Tags tag = tagsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found"));
            tags.add(tag);
        }

        Date date = new Date();
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(user);
        post.setDate(date);

        post.setTags(tags);

        postRepository.save(post);
        return post;
    }

    @Override
    public Post updatePost(UpdatePostDTO dto) {
        Post post = postRepository.findById(dto.postId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        User user = userService.findUserById(dto.userId());

        Set<Long> tags = Optional.ofNullable(dto.tagsId()).orElse(Set.of());

        // transfer to arraylist
        List<Tags> tagsList = new ArrayList<>();
        for (Long id : tags) {
            Tags tag = tagsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found"));
            tagsList.add(tag);
        }

        // verify if the user is the author of the post
        if (!Objects.equals(post.getAuthor().getId(), user.getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Failed to update post");
        }

        post.setTags(tagsList);
        post.setTitle(dto.title());
        post.setContent(dto.content());

        postRepository.save(post);
        return post;
    }


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

    @Override
    public List<HomePagePostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<HomePagePostDTO> homePagePostDTOS = new ArrayList<>();
        for (Post post : posts) {
            homePagePostDTOS.add(new HomePagePostDTO(post.getId(), post.getTitle(), post.getContent()));
        }
        return homePagePostDTOS;

    }

    @Override
    public List<Post> getUserPosts(Long userId) {
        User user = userService.findUserById(userId);
        return postRepository.findAllByAuthor_Id(user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User has no posts"));
    }

    @Override
    public Post getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        return post;
    }

}
