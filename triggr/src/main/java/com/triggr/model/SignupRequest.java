package com.triggr.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "SignupRequest")
public class SignupRequest {
    @Id
    private String id;
    private String email;
    private String otp;
    private LocalDateTime createDate;

    public SignupRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
        this.createDate = LocalDateTime.now();
    }
}