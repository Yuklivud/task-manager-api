package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TaskResponse", description = "DTO representing task details in response")
public record TaskResponse(
        @Schema(description = "Task ID", example = "123")
        Long id,

        @Schema(description = "Task title", example = "Finish report")
        String title,

        @Schema(description = "Task description", example = "Prepare the project report by Friday")
        String description,

        @Schema(description = "Task deadline", example = "2025-08-10T18:00:00")
        LocalDateTime deadline,

        @Schema(description = "Last update timestamp", example = "2025-08-05T15:30:00")
        LocalDateTime updatedAt,

        @Schema(description = "Task creation timestamp", example = "2025-08-01T12:00:00")
        LocalDateTime createdAt,

        @Schema(description = "User who created the task")
        UserResponse userResponse
) {}
