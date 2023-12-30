package com.example.namelessblog.rest.dto;

import java.util.Set;

public record UpdatePostDTO(String title, String content, Long userId, Long postId, Set<Long> tagsId) {
}
