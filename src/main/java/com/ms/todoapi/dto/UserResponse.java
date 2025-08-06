package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Set;


@Schema(name = "UserResponse", description = "DTO representing user details in response")
public record UserResponse(
        @Schema(description = "User ID", example = "42")
        Long id,

        @Schema(description = "User email", example = "user@example.com")
        String email,

        @Schema(description = "Is user active", example = "true")
        Boolean isActive,

        @Schema(description = "User creation date", example = "2023-05-01")
        LocalDate createdAt,

        @Schema(description = "User roles")
        Set<String> roles
) {}