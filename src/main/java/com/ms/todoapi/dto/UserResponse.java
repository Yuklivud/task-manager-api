package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "DTO for response")
public record UserResponse(
        Long id,
        String email,
        Boolean isActive,
        LocalDate createdAt,
        Set<String> roles
) {}