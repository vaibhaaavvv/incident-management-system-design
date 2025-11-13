package com.webhook.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "incidents")
public class Incident {
    @Id
    private String id;
    private String incidentDescription;
    private String customerId;
    private String projectId;
    private LocalDateTime creationTime;
    private String email;

    public Incident(String incidentDescription, String customerId, String projectId, String email) {
        this.incidentDescription = incidentDescription;
        this.customerId = customerId;
        this.projectId = projectId;
        this.email = email;
        this.creationTime = LocalDateTime.now();
    }
}