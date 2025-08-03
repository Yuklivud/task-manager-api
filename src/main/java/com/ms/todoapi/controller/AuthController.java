package com.ms.todoapi.controller;

import com.ms.todoapi.dto.AuthRequest;
import com.ms.todoapi.dto.AuthResponse;
import com.ms.todoapi.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody @Valid AuthRequest authRequest) {
        return authenticationService.signUp(authRequest);
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody @Valid AuthRequest authRequest) {
        return authenticationService.signin(authRequest);
    }
}
