package com.webhook.service;

import com.webhook.dto.IncidentRequest;
import com.webhook.model.Incident;
import com.webhook.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {
    
    @Autowired
    private IncidentRepository incidentRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private QueueService queueService;
    
    public void createIncident(IncidentRequest request, String apiKey) {
        String email = userService.getUserEmail(apiKey);
        
        Incident incident = new Incident(
            request.getIncidentDescription(),
            request.getCustomerId(),
            request.getProjectId(),
            email
        );
        
        // Persist to database
        incident = incidentRepository.save(incident);
        
        // Send to queue
        queueService.publishIncident(incident);
    }
}