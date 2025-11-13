package com.triggr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triggr.dto.InitRequest;
import com.triggr.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/init")
    public ResponseEntity<String> init(@Valid @RequestBody InitRequest request) {
        try {
            String apiKey = userService.init(request);
            return ResponseEntity.ok(apiKey);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Init failed: " + e.getMessage());
        }
    }
}