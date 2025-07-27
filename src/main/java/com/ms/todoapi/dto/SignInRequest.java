package com.ms.todoapi.dto;

public record SignInRequest (
        String email,
        String password)
{}
