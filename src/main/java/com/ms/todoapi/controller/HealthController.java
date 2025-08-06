package com.ms.todoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Health check endpoints")
public class HealthController {

    @Operation(summary = "Check service availability")
    @ApiResponse(responseCode = "200", description = "Service is running")
    @GetMapping({"/ping", "/health"})
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}

