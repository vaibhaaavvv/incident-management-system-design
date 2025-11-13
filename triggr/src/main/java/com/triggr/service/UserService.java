package com.triggr.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triggr.dto.InitRequest;
import com.triggr.model.User;
import com.triggr.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String init(InitRequest request) {
        String apiKey = UUID.randomUUID().toString();
        LocalDateTime timeOfGeneration = LocalDateTime.now();
        
        User user = new User(apiKey, timeOfGeneration, request.getEmail());
        userRepository.save(user);
        
        return apiKey;
    }
}