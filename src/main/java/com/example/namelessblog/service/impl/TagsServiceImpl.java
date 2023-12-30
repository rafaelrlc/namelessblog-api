package com.example.namelessblog.service.impl;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.Tags;
import com.example.namelessblog.domain.entity.User;
import com.example.namelessblog.repository.PostRepository;
import com.example.namelessblog.repository.TagsRepository;
import com.example.namelessblog.service.TagsService;
import com.example.namelessblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;


@Service
public class TagsServiceImpl implements TagsService {


    private final TagsRepository tagsRepository;
    private final PostRepository postRepository;


    public TagsServiceImpl(TagsRepository tagsRepository, PostRepository postRepository) {
        this.tagsRepository = tagsRepository;
        this.postRepository = postRepository;

    }


    @Override
    public void saveTags(Tags tag) {
        tagsRepository.save(tag);
    }

    @Override
    public Tags findTagsById(Long id) {
        return tagsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tags not found"));
    }

    @Override
    public void deleteTag(Long id) {
        Tags tagFromDB = tagsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Badge not found"));

        for (Post post : tagFromDB.getPostList()) {
            post.getTags().remove(tagFromDB);
            postRepository.save(post);
        }

        tagsRepository.deleteById(id);
    }


    @Override
    public List<Tags> findAllTags() {
        return tagsRepository.findAll();
    }
}
