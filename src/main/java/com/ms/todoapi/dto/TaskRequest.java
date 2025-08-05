package com.ms.todoapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRequest(
    @NotBlank String title,
    @NotBlank String description,
    @NotNull LocalDateTime deadline
)
{}
