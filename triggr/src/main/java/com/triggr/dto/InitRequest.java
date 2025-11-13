package com.triggr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InitRequest {
       @Email(message = "Invalid email format")
       @NotBlank(message = "Email is required")
       private String email;
}
