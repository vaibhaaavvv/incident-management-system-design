package com.webhook.service;

import com.webhook.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class IncidentListenerService {
    
    @Autowired
    private EmailService emailService;
    
    @Async
    @KafkaListener(topics = "incidents", groupId = "incident-email-group")
    public void processIncident(Incident incident) {
        if (incident.getEmail() != null) {
            emailService.sendIncidentEmail(incident);
        }
    }
}