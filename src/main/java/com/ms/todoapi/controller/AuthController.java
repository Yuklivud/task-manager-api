package com.ms.todoapi.controller;

import com.ms.todoapi.dto.AuthRequest;
import com.ms.todoapi.dto.AuthResponse;
import com.ms.todoapi.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication operations")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register new user")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody @Valid AuthRequest authRequest) {
        return authenticationService.signUp(authRequest);
    }

    @Operation(summary = "Sign in user and get JWT token")
    @ApiResponse(responseCode = "200", description = "User authenticated successfully")
    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody @Valid AuthRequest authRequest) {
        return authenticationService.signin(authRequest);
    }
}

