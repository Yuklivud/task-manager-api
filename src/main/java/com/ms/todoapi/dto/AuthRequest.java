package com.ms.todoapi.dto;

public record AuthRequest(
        String email,
        String password)
{}
