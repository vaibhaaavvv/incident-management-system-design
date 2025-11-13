package com.webhook.service;

import com.webhook.model.Incident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueService {
    
    @Autowired
    private KafkaTemplate<String, Incident> kafkaTemplate;
    
    private static final String TOPIC = "incidents";
    
    public void publishIncident(Incident incident) {
        kafkaTemplate.send(TOPIC, incident);
    }
}