package com.example.demo.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {

    private String email;

    private UUID id;

    private String message;

    private String token;

    private long expiresIn;

}