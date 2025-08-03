package com.ms.todoapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {
    @GetMapping({"/ping", "/health"})
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
