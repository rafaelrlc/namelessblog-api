package com.example.namelessblog.service;

import com.example.namelessblog.domain.entity.Post;
import com.example.namelessblog.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    void saveUser(User user);

}
