package com.ms.todoapi.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        Integer status,
        String message,
        LocalDateTime timestamp)
{}
