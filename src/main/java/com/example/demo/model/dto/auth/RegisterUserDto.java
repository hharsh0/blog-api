package com.example.demo.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;
}
