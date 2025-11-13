package com.triggr.service;

import com.triggr.model.User;
import com.triggr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String init() {
        String apiKey = UUID.randomUUID().toString();
        LocalDateTime timeOfGeneration = LocalDateTime.now();
        
        User user = new User(apiKey, timeOfGeneration);
        userRepository.save(user);
        
        return apiKey;
    }
}