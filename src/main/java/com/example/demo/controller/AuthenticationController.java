package com.example.demo.controller;

import com.example.demo.model.dto.auth.LoginUserDto;
import com.example.demo.model.dto.auth.RegisterUserDto;
import com.example.demo.model.User;
import com.example.demo.model.dto.auth.LoginResponse;
import com.example.demo.model.dto.auth.RegisterUserResponse;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        RegisterUserResponse response = RegisterUserResponse.builder()
                .id(registeredUser.getId())
                .username(registeredUser.getFullName())
                .email(registeredUser.getEmail())
                .createdAt(registeredUser.getCreatedAt())
                .message("User Successfully registered!")
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}