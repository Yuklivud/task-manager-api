package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "DTO for API error responses")
public record ErrorResponse(
        @Schema(description = "HTTP status code", example = "404")
        Integer status,

        @Schema(description = "Error message", example = "Resource not found")
        String message,

        @Schema(description = "Timestamp when the error occurred")
        LocalDateTime timestamp
) {}
