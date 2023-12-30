package com.example.namelessblog.service;

import com.example.namelessblog.domain.entity.Tags;

import java.util.List;

public interface TagsService {

    void saveTags(Tags tag);

    Tags findTagsById(Long id);

    void deleteTag(Long id);

    List<Tags> findAllTags();
}
