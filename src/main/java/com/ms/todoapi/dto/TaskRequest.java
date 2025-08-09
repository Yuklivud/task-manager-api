package com.ms.todoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


@Schema(name = "TaskRequest", description = "DTO for creating or updating a task")
public record TaskRequest(
        @NotBlank
        @Schema(description = "Task title", example = "Finish report")
        String title,

        @NotBlank
        @Schema(description = "Task description", example = "Prepare the project report by Friday")
        String description,

        @NotNull
        @Schema(description = "Task deadline", example = "2025-08-10T18:00:00")
        LocalDateTime deadline
) {}
