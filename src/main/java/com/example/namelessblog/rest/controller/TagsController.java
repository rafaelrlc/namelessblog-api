package com.example.namelessblog.rest.controller;


import com.example.namelessblog.domain.entity.Tags;
import com.example.namelessblog.service.TagsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagsController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public ResponseEntity<List<Tags>> getAllTags() {
        return new ResponseEntity<>(tagsService.findAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tags> getTagById(@PathVariable Long id) {
        return new ResponseEntity<>(tagsService.findTagsById(id), HttpStatus.OK);
    }

    @PostMapping()
    public void newTag(@RequestBody Tags tag) {
        tagsService.saveTags(tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagsService.deleteTag(id);
    }
}
