package com.example.namelessblog.rest.dto;


import java.util.Set;

public record PostDTO(String title, String content, Long userId, Set<Long> tagsId) {

}
