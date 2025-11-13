package com.webhook.service;

import com.webhook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ApiKeyValidationService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final ConcurrentHashMap<String, Boolean> apiKeyCache = new ConcurrentHashMap<>();
    
    public boolean isValidApiKey(String apiKey) {
        // Check in-memory cache first
        if (apiKeyCache.containsKey(apiKey)) {
            return apiKeyCache.get(apiKey);
        }
        
        // Query database if not in cache
        boolean isValid = userRepository.findByApiKey(apiKey).isPresent();
        
        // Cache the result
        apiKeyCache.put(apiKey, isValid);
        
        return isValid;
    }
}