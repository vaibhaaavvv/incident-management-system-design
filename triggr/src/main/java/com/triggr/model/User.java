package com.triggr.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String apiKey;
    private LocalDateTime timeOfGeneration;

    public User(String apiKey, LocalDateTime timeOfGeneration) {
        this.apiKey = apiKey;
        this.timeOfGeneration = timeOfGeneration;
    }
}