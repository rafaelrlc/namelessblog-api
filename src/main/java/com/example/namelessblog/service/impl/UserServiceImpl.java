package com.example.namelessblog.service.impl;

import com.example.namelessblog.repository.UserRepository;
import com.example.namelessblog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
