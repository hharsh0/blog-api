package com.example.demo.model.dto.auth;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String fullName;
    private String email;
}
