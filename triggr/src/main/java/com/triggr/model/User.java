package com.triggr.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String apiKey;
    private String email;
    private LocalDateTime timeOfGeneration;

    public User(String apiKey, LocalDateTime timeOfGeneration, String email) {
        this.apiKey = apiKey;
        this.timeOfGeneration = timeOfGeneration;
        this.email = email;
    }
}