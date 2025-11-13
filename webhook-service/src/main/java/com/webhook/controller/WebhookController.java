package com.webhook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webhook.dto.IncidentRequest;
import com.webhook.service.ApiKeyValidationService;
import com.webhook.service.IncidentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/webhook")
public class WebhookController {
    
    @Autowired
    private ApiKeyValidationService apiKeyValidationService;
    @Autowired
    private IncidentService incidentService;
    
    @PostMapping("/incident")
    public ResponseEntity<String> receiveIncident(
            @RequestHeader("X-API-Key") String apiKey,
            @Valid @RequestBody IncidentRequest request) {
        
        // Validate API key
        if (!apiKeyValidationService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key");
        }
        
        incidentService.createIncident(request, apiKey);
        
        return ResponseEntity.ok("Request processed");
    }
}