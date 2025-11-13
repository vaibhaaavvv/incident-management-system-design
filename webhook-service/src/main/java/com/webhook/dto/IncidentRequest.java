package com.webhook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IncidentRequest {
    @NotBlank(message = "Incident description is required")
    private String incidentDescription;
    
    private String customerId;
    private String projectId;
}