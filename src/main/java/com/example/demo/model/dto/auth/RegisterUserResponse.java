package com.example.demo.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RegisterUserResponse {
    private UUID id;
    private String username;
    private String email;
    private Date createdAt;
    private String message;
}
