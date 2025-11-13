package com.webhook.service;

import com.webhook.model.User;
import com.webhook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    private final ConcurrentHashMap<String, String> emailCache = new ConcurrentHashMap<>();
    
    public String getUserEmail(String apiKey) {
        // Check cache first
        if (emailCache.containsKey(apiKey)) {
            return emailCache.get(apiKey);
        }
        
        // Query database if not in cache
        User user = userRepository.findByApiKey(apiKey).orElse(null);
        if (user != null && user.getEmail() != null) {
            // Cache the email
            emailCache.put(apiKey, user.getEmail());
            return user.getEmail();
        }
        
        return null;
    }
}