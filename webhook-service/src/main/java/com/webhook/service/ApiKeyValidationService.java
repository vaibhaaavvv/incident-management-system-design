package com.webhook.service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webhook.model.User;
import com.webhook.repository.UserRepository;

@Service
public class ApiKeyValidationService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final ConcurrentHashMap<String, String> apiKeyCache = new ConcurrentHashMap<>();
    
    public boolean isValidApiKey(String apiKey) {
        // Check in-memory cache first
        if (apiKeyCache.containsKey(apiKey)) {
            return true;
        }
        
        // Query database if not in cache
        Optional<User> user = userRepository.findByApiKey(apiKey);

        if(!user.isPresent()) {
            return false;
        }
        
        // Cache the result
        apiKeyCache.put(apiKey, user.get().getEmail());
        
        return true;
    }
}