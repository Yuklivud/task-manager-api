package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "DTO for task response")
public record TaskResponse(
    Long id,
    String title,
    String description,
    LocalDateTime deadline,
    LocalDateTime updatedAt,
    LocalDateTime createdAt,
    UserResponse userResponse
) {}
