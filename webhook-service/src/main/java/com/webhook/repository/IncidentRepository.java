package com.webhook.repository;

import com.webhook.model.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncidentRepository extends MongoRepository<Incident, String> {
}